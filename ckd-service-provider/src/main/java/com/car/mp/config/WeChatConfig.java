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
public class WeChatConfig {
	/**
	 *  wx_appid=wx8774f3abd363f5ab
		wx_appsecret=f889f27be02f2733c3031ef1c1f1956f
		wx_token=SYNCML
		wx_aeskey=gLG8qjVbncG8iniE1NxXG5l2E5Fl7KrhhJLDDEfPMPR
		zCOHA9z4TgZhB0r4etxYHTiCd-1eRuTO_fzO5IC8_E2IL_HKfRDUH9rMp0GCB6Emhuyp2rrLYYhuZnUSp-d9Q16fEgEqaSywPkyazKSfDEGBDTSYE5owT2jpGz34ruSsEVXdAHAQHJ
        gKp1EBkaiL28sCGmLlvLWaXRLalF8KCWVbi7_qY3BtZowDorOn2uPfRXYWZsl6fZhGSKXxRn8OquaVJNuAV3vI9gN7qo_QEXcKK8YdlbGHkNOFaAIAHIZ
	 */
    //TODO 填写公众号开发信息
    //商站宝测试公众号 APP_ID
    protected static final String APP_ID = "wxb4c7174c06f62afe";
    //商站宝测试公众号 APP_SECRET
    protected static final String APP_SECRET = "bda472021b9c2adc959d57e367a078fe";
    //商站宝测试公众号 TOKEN
    protected static final String TOKEN = "weixinkaifaxx";
    //商站宝测试公众号 AES_KEY
    protected static final String AES_KEY = "jRWkDdaQN8KEm5GnkGmMS7NJicWcoCO04rLDmMA479t";
    //商站宝微信支付商户号
    protected static final String PARTNER_ID = "";
    //商站宝微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
    protected static final String PARTNER_KEY = "";

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

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(this.appid);
        configStorage.setSecret(this.appsecret);
        configStorage.setToken(this.token);
        configStorage.setAesKey(this.aesKey);
        configStorage.setPartnerId(this.partenerId);
        configStorage.setPartnerKey(this.partenerKey);

//        configStorage.setAppId(MainConfig.APP_ID);
//        configStorage.setSecret(MainConfig.APP_SECRET);
//        configStorage.setToken(MainConfig.TOKEN);
//        configStorage.setAesKey(MainConfig.AES_KEY);
//        configStorage.setPartnerId(MainConfig.PARTNER_ID);
//        configStorage.setPartnerKey(MainConfig.PARTNER_KEY);

        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
}
