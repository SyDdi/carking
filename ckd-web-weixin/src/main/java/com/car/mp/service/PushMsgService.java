package com.car.mp.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.*;
import com.car.mp.config.ApplicationConfig;
import com.car.security.HashIdsHelper;
import com.car.service.*;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2016/12/28.
 */
@Service
public class PushMsgService {
    @Reference
    IVehicleService vehicleService;//调用Dubbo暴露的接口
    @Reference
    IUserService iUserService;
    @Reference
    IModelService iModelService;
    @Reference
    ICollectionService iCollectionService;
    @Reference
    IWechatService wechatService;
    @Reference
    private IWeixinPushMsgService iWeixinPushMsgService;
    @Autowired
    protected WxMpService wxMpService;
    /**
     *  销售推送的时候 需要把消息推送到所有收藏该车的用户
     */
    public void sendWeiXinMessage(Long vhclId, User user, String content){
        try {
            Vehicle vehicle = vehicleService.selectByKey(vhclId);
            Model model = iModelService.selectByKey(Integer.valueOf(vehicle.getModelId() + ""));//由于重载，要转成Integer类型
            Collection collect = new Collection();
            collect.setVehicleId(vhclId);
            collect.setStatus((short)1);
            List<Collection> collList = iCollectionService.select(collect);
            User ownerUser = iUserService.selectByKey(vehicle.getUserId()); //车主也推送消息
            collect.setUserId(vehicle.getUserId());
            collList.add(collect);
            for(int i = 0 ; i< collList.size() ; i++) {//发送给所有收藏该车的人
                User use = iUserService.selectByKey(collList.get(i).getUserId());//车主信息
                if (use.getId() != user.getId() && !use.getId().equals(user.getId())) {//不要推送给自己
                    if (use.getSubscribe() == 1) {//必须订阅公众号
                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                        templateMessage.setToUser(use.getOpenId());
                        templateMessage.setTemplateId(ApplicationConfig.MESSAGE_TEMPLATE);
//						templateMessage.setUrl(ApplicationConfig.WEIXINURL + "/car/" + vhclId + ".html");
                        //  因为 车主发布车源后 很长一段时间 不操作 服务器 session 失效后再访问 该页面 会导致看不到车主应该看到的信息 故：用下面這种方式  调用链接时获得code在LoginInteceptor拦截器中获取用户信息
                        templateMessage.setUrl(wxMpService.oauth2buildAuthorizationUrl(ApplicationConfig.WEIXINURL + "/car/" + HashIdsHelper.encode(vhclId) + ".html", "snsapi_base", ""));
                        templateMessage.setTopColor("#4C4C4C");
                        String first ="["+ownerUser.getNickName()+"] "+ model.getCarYear() + "款 " + model.getBrand() + " " + model.getFamilyGroupName() + " " + model.getFamily() + " " + model.getShortName();
                        String userContent =  user.getNickName() + "  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        templateMessage.getData().add(new WxMpTemplateData("first",first, "#4C4C4C"));
                        templateMessage.getData().add(new WxMpTemplateData("user",userContent, "#4C4C4C"));
                        templateMessage.getData().add(new WxMpTemplateData("ask", content, "#4C4C4C"));
                        templateMessage.getData().add(new WxMpTemplateData("remark", "点击查看新留言详情", "#019BFF"));
                        String result = wechatService.sendTemplateMsg(templateMessage);
                        WeixinPushMsg wxp = new WeixinPushMsg();
                        wxp.setStatus(0);
                        if (result!=null && !"".equals(result)){
                            wxp.setSendId(result);
                            wxp.setStatus(1);
                        }
                        wxp.setType(2);
                        wxp.setCreateDate(new Date());
                        wxp.setUserId(use.getId());
                        wxp.setVehicleId(vehicle.getId());
                        wxp.setSendUser(0L);
                        wxp.setContent(first+"###"+ user+"###"+content+"###点击查看新留言详情");
                        iWeixinPushMsgService.insert(wxp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *  自定义推送消息给后台人员
     * @param vhclId 车辆id
     * @param message 内容
     * @param title title
     */
    public String messagePush(Long vhclId ,String title,String message ,String remark){
        try {
            // 推送信息给后台工作人员
            User user = new User();
            user.setType(3);
            user.setSubscribe(1);
            List<User> list = iUserService.select(user);
            for(User u : list) {
                WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                templateMessage.setToUser(u.getOpenId());
                templateMessage.setTemplateId(ApplicationConfig.CONTENT_PUSHING);
                templateMessage.setUrl(wxMpService.oauth2buildAuthorizationUrl(ApplicationConfig.WEIXINURL + "/car/" + HashIdsHelper.encode(vhclId) + ".html", "snsapi_base", ""));
                templateMessage.setTopColor("#4C4C4C");

                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
                templateMessage.getData().add(new WxMpTemplateData("first", title, "#4C4C4C"));
                templateMessage.getData().add(new WxMpTemplateData("keyword1", message, "#4C4C4C"));
                templateMessage.getData().add(new WxMpTemplateData("keyword2", time, "#4C4C4C"));
                templateMessage.getData().add(new WxMpTemplateData("remark", remark, "#019BFF"));
                String result = wechatService.sendTemplateMsg(templateMessage);
                // 发送消息保存到DB中
                WeixinPushMsg wxp = new WeixinPushMsg();
                wxp.setStatus(0);
                if (result != null && !"".equals(result)) {
                    wxp.setSendId(result);
                    wxp.setStatus(1);
                }
                wxp.setType(4);
                wxp.setCreateDate(new Date());
                wxp.setUserId(u.getId());
                wxp.setVehicleId(vhclId);
                wxp.setSendUser(0L);
                wxp.setContent(title + "###" + message + "###" + time + "###" + remark);
                iWeixinPushMsgService.insert(wxp);
            }
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
