package com.car.mp.service.wx.handler;

import com.car.core.utils.Helper;
import com.car.domain.User;
import com.car.mp.config.WechatConfig;
import com.car.mp.service.UserService;
import com.car.mp.service.wx.CoreService;
import com.car.mp.util.CommonUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户关注公众号Handler
 *
 * Created by FirenzesEagle on 2016/7/27 0027.
 * Email:liumingbo2008@gmail.com
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    protected WxMpConfigStorage configStorage;
    @Autowired
    protected WxMpService wxMpService;
    @Autowired
    protected CoreService coreService;

    @Autowired
    UserService userService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        WxMpUser wxMpUser = coreService.getUserInfo(wxMessage.getFromUser(), "zh_CN");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String nickName  = CommonUtil.filterEmoji(wxMpUser.getNickname());
        params.add(new BasicNameValuePair("openId", wxMpUser.getOpenId()));
        params.add(new BasicNameValuePair("nickname", nickName));
        params.add(new BasicNameValuePair("headImgUrl", wxMpUser.getHeadImgUrl()));

        //TODO 在这里可以进行用户关注时对业务系统的相关操作（比如新增用户）
        User user = userService.getUserByOpenId(wxMpUser.getOpenId());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.logger.info("\n用户ID="+ Helper.getIP(request));


        if(user==null){
            user = new User();
            user.setOpenId(wxMpUser.getOpenId());
            user.setNickName(nickName);
            user.setHeadImgUrl(wxMpUser.getHeadImgUrl());
            user.setSubscribe(1);
            user.setCreated(new Date());
            userService.createUser(user);
        }else{
            user.setNickName(nickName);
            user.setHeadImgUrl(wxMpUser.getHeadImgUrl());
            user.setSubscribe(1);
            userService.updateUser(user);
        }
        WxMpXmlOutTextMessage m
                = WxMpXmlOutMessage.TEXT()
                .content(wxMpUser.getNickname()+WechatConfig.WELCOME_MSG)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        logger.info("subscribeMessageHandler" + m.getContent());
        return m;
    }

};
