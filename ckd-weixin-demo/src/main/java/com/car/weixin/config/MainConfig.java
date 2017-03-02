package com.car.weixin.config;

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
public class MainConfig {
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
    protected static final String APP_ID = "wxcd1e24f5b6cbfeb5";
    //商站宝测试公众号 APP_SECRET
    protected static final String APP_SECRET = "b6414228042a59f73f761bb7156e6020";

    //商站宝测试公众号 TOKEN
    protected static final String TOKEN = "SYNCML";
    //商站宝测试公众号 AES_KEY
    protected static final String AES_KEY = "jRWkDdaQN8KEm5GnkGmMS7NJicWcoCO04rLDmMA479t";
    //商站宝微信支付商户号
    protected static final String PARTNER_ID = "";
    //商站宝微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
    protected static final String PARTNER_KEY = "";


    public static final String WELCOME_MSG = "欢迎使用“行情价”，我们提供简单有效的二手车估值服务及其解决方案。" +
            " \n" + "有任何建议和需求，请直接在公众号中微信我们。" ;


    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();

        configStorage.setAppId(MainConfig.APP_ID);
        configStorage.setSecret(MainConfig.APP_SECRET);
        configStorage.setToken(MainConfig.TOKEN);
        configStorage.setAesKey(MainConfig.AES_KEY);
        configStorage.setPartnerId(MainConfig.PARTNER_ID);
        configStorage.setPartnerKey(MainConfig.PARTNER_KEY);

        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
}
