package com.car.mp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ApplicationConfig {

    //网站的域名
    @Value("${DOMAIN}")
    public static String DOMAIN;

    //数据库中图片对应的路径
    @Value("${IMGPATH}")
    public static String IMGPATH;

    //数据库中图片对应的路径
    @Value("${PORTAL}")
    public static String PORTAL;
    /**
     * 访客来咨询通知模板Id
     */
    @Value("${MESSAGE_TEMPLATE}")
    public static String MESSAGE_TEMPLATE;

    /**
     * 订单更新提醒
     */
    @Value("${UPDATE_ORDER_TO_REMIND}")
    public static String UPDATE_ORDER_TO_REMIND;

    /**
     * 推送信息给admin用户
     */
    @Value("${CONTENT_PUSHING}")
    public static String CONTENT_PUSHING;
    /**
     * 微信URL
     */
    @Value("${WEIXINURL}")
    public static String WEIXINURL;

//    /**
//     * 微信appId
//     */
//    @Value("${wechat.appid}")
//    public static String wechatappid;

    /**
     * 微信appId
     */
    @Value("${WECHATAPPID}")
    public static String WECHATAPPID;

}
