package com.car.mp.service.wx.handler;

import com.car.domain.User;
import com.car.mp.service.UserService;
import com.car.mp.service.wx.CoreService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户取消关注公众号Handler
 */
@Component
public class UnSubscribeHandler extends AbstractHandler {

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

        //TODO 在这里可以进行用户取消关注时对业务系统的相关操作（比如新增用户）
        User user = userService.getUserByOpenId(wxMessage.getFromUser());
        if(user!=null){
            user.setSubscribe(0);
            userService.updateUser(user);
        }
        this.logger.info("\n用户取消关注，OPENDI：【{}】", wxMessage.getFromUser());
        WxMpXmlOutTextMessage m
                = WxMpXmlOutMessage.TEXT()
                .content("欢迎下次再来")
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return m;
    }
};
