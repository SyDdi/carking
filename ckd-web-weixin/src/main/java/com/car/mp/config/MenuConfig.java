package com.car.mp.config;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * Created by FirenzesEagle on 2016/6/1 0001.
 * Email:liumingbo2008@gmail.com
 */
public class MenuConfig {

    private static final String DOMAIN = "syncml.tunnel.qydev.com";
//    private static final String DOMAIN = "m.andaxauto.com";
//    private static final String DOMAIN = "sywenxindemo.tunnel.qydev.com";
    /**
     * 定义菜单结构
     *
     * @return
     */
    protected static WxMenu getMenu() {

        WechatConfig wechatConfig = new WechatConfig();
        WxMpService wxMpService = wechatConfig.wxMpService();

        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.BUTTON_VIEW);
        button1.setName("估值");
        button1.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://"+DOMAIN+"/pinggu", "snsapi_base", ""));

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.BUTTON_VIEW);
        button2.setName("我的车");
        button2.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://"+DOMAIN+"/my", "snsapi_base", ""));

        WxMenuButton  b3 = new WxMenuButton();
        b3.setType(WxConsts.BUTTON_VIEW);
        b3.setName("其他");
        b3.setKey("other");

        WxMenuButton b31 = new WxMenuButton();
        b31.setType(WxConsts.BUTTON_VIEW);
        b31.setName("商务合作");
        b31.setUrl("http://"+DOMAIN+"/static/cooperate.html");

        WxMenuButton b32 = new WxMenuButton();
        b32.setType(WxConsts.BUTTON_VIEW);
        b32.setName("新车价");
        b32.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://"+DOMAIN+"/price/list.html", "snsapi_base", ""));

        b3.getSubButtons().add(b31);
        b3.getSubButtons().add(b32);
//        WxMenuButton button31 = new WxMenuButton();
//        button31.setType(WxConsts.BUTTON_VIEW);
//        button31.setName("OPENID");
//        button31.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://syncml.tunnel.qydev.com/getOpenid", "snsapi_base", ""));
//
//        WxMenuButton button32 = new WxMenuButton();
//        button32.setType(WxConsts.BUTTON_VIEW);
//        button32.setName("OAUTH");
//        button32.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://syncml.tunnel.qydev.com/getOAuth2UserInfo?lang=zh_CN", "snsapi_base", ""));
//
//        WxMenuButton button33 = new WxMenuButton();
//        button33.setType(WxConsts.BUTTON_VIEW);
//        button33.setName("USERINFO");
//        button33.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://syncml.tunnel.qydev.com/getUserInfo?lang=zh_CN", "snsapi_base", ""));
//
//        button3.getSubButtons().add(button31);
//        button3.getSubButtons().add(button32);
//        button3.getSubButtons().add(button33);

        /**
         WxMenuButton button2 = new WxMenuButton();
         button2.setName("我是卖家");

         WxMenuButton button21 = new WxMenuButton();
         button21.setType(WxConsts.BUTTON_VIEW);
         button21.setName("我的订单");
         button21.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://syncml.tunnel.qydev.com/getOpenid", "snsapi_base", ""));

         WxMenuButton button22 = new WxMenuButton();
         button22.setType(WxConsts.BUTTON_VIEW);
         button22.setName("收入统计");
         button22.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://syncml.tunnel.qydev.com/getOAuth2UserInfo", "snsapi_base", ""));

         WxMenuButton button23 = new WxMenuButton();
         button23.setType(WxConsts.BUTTON_VIEW);
         button23.setName("发布商品");
         button23.setUrl(wxMpService.oauth2buildAuthorizationUrl("http://syncml.tunnel.qydev.com/getUserInfo", "snsapi_base", ""));

         WxMenuButton button24 = new WxMenuButton();
         button24.setType(WxConsts.BUTTON_VIEW);
         button24.setName("商品管理");
         button24.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));

         button2.getSubButtons().add(button21);
         button2.getSubButtons().add(button22);
         button2.getSubButtons().add(button23);
         button2.getSubButtons().add(button24);

         WxMenuButton button3 = new WxMenuButton();
         button3.setType(WxConsts.BUTTON_CLICK);
         button3.setName("使用帮助");
         button3.setKey("help");
         **/
        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(b3);
        return menu;
    }

    /**
     * 运行此main函数即可生成公众号自定义菜单
     *
     * @param args
     */
    public static void main(String[] args) {
        //强烈禁止运行改方法
        //强烈禁止运行改方法
        //强烈禁止运行改方法
        //强烈禁止运行改方法//强烈禁止运行改方法
        //强烈禁止运行改方法
        //强烈禁止运行改方法//强烈禁止运行改方法
        //强烈禁止运行改方法
        //强烈禁止运行改方法//强烈禁止运行改方法
        //强烈禁止运行改方法
        //强烈禁止运行改方法

         WechatConfig wechatConfig = new WechatConfig();
        WxMpService wxMpService = wechatConfig.wxMpService();
        try {
            wxMpService.getMenuService().menuDelete();//先清除，在重建
            wxMpService.getMenuService().menuCreate(getMenu());

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        System.out.println("菜单创建结束");
    }

}
