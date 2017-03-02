package com.car.mp.test;

import com.car.mp.config.ApplicationConfig;
import com.car.mp.config.WechatConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 2016/11/29.
 */
public class ConfigTest extends BasicTest {

    @Test
    public void test() {

        System.out.println(ApplicationConfig.DOMAIN);
        System.out.println(ApplicationConfig.IMGPATH);


//        WechatConfig wechatConfig = new WechatConfig();
//        WxMpService wxMpService = wechatConfig.wxMpService();



    }

}
