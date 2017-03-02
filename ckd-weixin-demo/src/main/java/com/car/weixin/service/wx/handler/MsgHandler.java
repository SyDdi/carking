package com.car.weixin.service.wx.handler;

import com.car.weixin.config.MainConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 转发客户消息Handler
 *
 * Created by FirenzesEagle on 2016/7/27 0027.
 * Email:liumingbo2008@gmail.com
 */
@Component
public class MsgHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
   	
        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词h
        if (StringUtils.endsWithIgnoreCase(wxMessage.getContent(), "h")) {
            WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
            .content(MainConfig.WELCOME_MSG)
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser())
            .build();
            return m;
        }

        return WxMpXmlOutMessage.TEXT().content(MainConfig.WELCOME_MSG).fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();

//    	return WxMpXmlOutMessage
//                .TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUserName())
//                .toUser(wxMessage.getFromUserName()).build();
    }
}


