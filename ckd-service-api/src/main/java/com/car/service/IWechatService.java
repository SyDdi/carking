package com.car.service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface IWechatService {

    /**
     * 发送模板消息
     * @param templateMessage
     * @return
     * @throws WxErrorException
     *
     * Example:
     *
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(...);
        templateMessage.setTemplateId(...);
        templateMessage.setUrl(...);
        templateMessage.setTopColor(...);
        templateMessage.getData().add(new WxMpTemplateData(name1, value1, color2));
        templateMessage.getData().add(new WxMpTemplateData(name2, value2, color2));
     *
     */
    public String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException;

}
