package com.car.mp.service.wx.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.mp.config.WechatConfig;
import com.car.mp.service.wx.CoreService;
import com.car.service.IImageService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
/**
 * 转发客户消息Handler
 *
 * Created by FirenzesEagle on 2016/7/27 0027.
 * Email:liumingbo2008@gmail.com
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Reference
    IImageService imageService;
    @Autowired
    protected CoreService coreService;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        WxMpUser wxMpUser = coreService.getUserInfo(wxMessage.getFromUser(), "zh_CN");
        if(wxMessage.getMsgType().equals(WxConsts.XML_MSG_IMAGE)){
            File file = wxMpService.getMaterialService().mediaDownload(wxMessage.getMediaId());
            //保存文件到临时文件夹/tmp

            String tmpFile = null;
            if("\\".equals(File.separator)){
                tmpFile = "c:/"+file.getName();
            }else{
                tmpFile = "/tmp/"+file.getName();
            }
            try {
                InputStream is = new FileInputStream(file);
                FileUtils.copyInputStreamToFile(is, new File(tmpFile));
                //做打马赛克处理
                int count = imageService.mosaicPlate(tmpFile);
                if(count>0){
                    file = new File(tmpFile);
                    WxMediaUploadResult res = wxMpService.getMaterialService().mediaUpload(WxConsts.XML_MSG_IMAGE, file);
                    res.getMediaId();
                    WxMpXmlOutImageMessage m = WxMpXmlOutMessage.IMAGE().mediaId(res.getMediaId())
                            .fromUser(wxMessage.getToUser())
                            .toUser(wxMessage.getFromUser())
                            .build();
                    return m;
                }else{
                    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
                            .content(getRandomMessage())
                            .fromUser(wxMessage.getToUser())
                            .toUser(wxMessage.getFromUser())
                            .build();
                    return m;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            //todo
        }
        //当用户输入关键词h
        if (StringUtils.endsWithIgnoreCase(wxMessage.getContent(), "h")) {
            WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
            .content(wxMpUser.getNickname()+WechatConfig.WELCOME_MSG)
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser())
            .build();
            return m;
        }
/*

        return WxMpXmlOutMessage.TEXT().content(WechatConfig.WELCOME_MSG).fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
*/

    	return WxMpXmlOutMessage
                .TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
    }


    private String messages[] = new String[]{
            "[闭嘴] 我眼前一黑，牌照没找到。",
            "[发呆] 抱歉，没能处理成功。",
            "[害羞] 你发的这是神马，小女子认不出来。",
            "[敲打] 为什么发这么难看的照片，我处理不了。",
            "[衰] 我不行了。。。"
    };

    private String getRandomMessage(){
        int x=(int)(Math.random()*messages.length);
        return messages[x];
    }
}


