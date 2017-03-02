package com.car.mp.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */

@Configuration
public class WechatConfig {


    //=========================== 微信配置部分 ==============================

//    //TODO 填写公众号开发信息
//    //商站宝测试公众号 APP_ID
//    protected static final String APP_ID = "wxb4c7174c06f62afe";
//    //商站宝测试公众号 APP_SECRET
//    protected static final String APP_SECRET = "bda472021b9c2adc959d57e367a078fe";
//    //商站宝测试公众号 TOKEN
//    protected static final String TOKEN = "weixinkaifaxx";
//    //商站宝测试公众号 AES_KEY
//    protected static final String AES_KEY = "jRWkDdaQN8KEm5GnkGmMS7NJicWcoCO04rLDmMA479t";
//    //商站宝微信支付商户号
//    protected static final String PARTNER_ID = "";
//    //商站宝微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
//    protected static final String PARTNER_KEY = "";


//
    //商站宝测试公众号 APP_ID
    protected static final String APP_ID ="wxdcdd5069ef1119c1";
    //商站宝测试公众号 APP_SECRET
    protected static final String APP_SECRET = "5ba3fd689bad75660c043179552bc5ba";

    //商站宝测试公众号 TOKEN
    protected static final String TOKEN = "shenyong";
    //商站宝测试公众号 AES_KEY
    protected static final String AES_KEY = "";
    //商站宝微信支付商户号
    protected static final String PARTNER_ID = "";
    //商站宝微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
    protected static final String PARTNER_KEY = "";
//    商站宝测试公众号 APP_ID
//    protected static final String APP_ID ="wxcd1e24f5b6cbfeb5";
//    //商站宝测试公众号 APP_SECRET
//    protected static final String APP_SECRET = "b6414228042a59f73f761bb7156e6020";
//
//    //商站宝测试公众号 TOKEN
//    protected static final String TOKEN = "SYNCML";
//    //商站宝测试公众号 AES_KEY
//    protected static final String AES_KEY = "gLG8qjVbncG8iniE1NxXG5l2E5Fl7KrhhJLDDEfPMPR";
//    //商站宝微信支付商户号
//    protected static final String PARTNER_ID = "";
//    //商站宝微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
//    protected static final String PARTNER_KEY = "";


    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.appsecret}")
    private String appsecret;

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.aeskey}")
    private String aesKey;

    @Value("${wechat.partener_id}")
    private String partenerId;

    @Value("${wechat.partener_key}")
    private String partenerKey;

    //public static final StrinWELCOME_MSGg WELCOME_MSG = "“行情价”，人工智能的二手车估值，便捷自助的车源发布及交易服务。\n" +
    //       "\n可信的车，可靠的人。";

    public static final String WELCOME_MSG = "，欢迎访问行情价。不管您是要卖掉、还是买进一部车，或者了解自己爱车的目前价值，都可以通过行情价的多种方式获得超级清楚的合理交易价格。";

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(this.appid);
        configStorage.setSecret(this.appsecret);
        configStorage.setToken(this.token);
        configStorage.setAesKey(this.aesKey);
        configStorage.setPartnerId(this.partenerId);
        configStorage.setPartnerKey(this.partenerKey);

//        configStorage.setAppId(WechatConfig.APP_ID);
//        configStorage.setSecret(WechatConfig.APP_SECRET);
//        configStorage.setToken(WechatConfig.TOKEN);
//        configStorage.setAesKey(WechatConfig.AES_KEY);
//        configStorage.setPartnerId(WechatConfig.PARTNER_ID);
//        configStorage.setPartnerKey(WechatConfig.PARTNER_KEY);

        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

}
