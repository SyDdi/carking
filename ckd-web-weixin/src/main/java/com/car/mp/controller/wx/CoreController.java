package com.car.mp.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.utils.JsonUtils;
import com.car.domain.User;
import com.car.mp.controller.GenericController;
import com.car.mp.service.UserService;
import com.car.service.IWechatService;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.mp.service.wx.CoreService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by FirenzesEagle on 2016/5/30 0030.
 * Email:liumingbo2008@gmail.com
 */
@Controller
public class CoreController extends GenericController {

    @Autowired
    protected WxMpConfigStorage configStorage;
    @Autowired
    protected WxMpService wxMpService;
    @Autowired
    protected CoreService coreService;
    @Autowired
    protected UserService userService;

    /**
     * 微信公众号webservice主服务接口，提供与微信服务器的信息交互
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/wx/core")
    public void wechatCore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            response.getWriter().println("非法请求");
            return;
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            response.getWriter().println(echostr);
            return;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?"raw" :request.getParameter("encrypt_type");

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = this.coreService.route(inMessage);
            response.getWriter().write(outMessage.toXml());
            return;
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                request.getInputStream(), this.configStorage, timestamp, nonce,
                msgSignature);
            this.LOG.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.coreService.route(inMessage);
            this.LOG.info(response.toString());
            response.getWriter()
                .write(outMessage.toEncryptedXml(this.configStorage));
            return;
        }

        response.getWriter().println("不可识别的加密类型");
        return;
    }

    /**
     * 通过openid获得基本用户信息
     * 详情请见: http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
     *
     * @param openid   openid
     * @param lang     zh_CN, zh_TW, en
     */
    @RequestMapping(value = "getUserInfo")
    public String getUserInfo(@RequestParam(value = "openid") String openid, @RequestParam(value = "lang") String lang,@RequestParam(value = "code") String code,ModelMap modelMap,HttpServletRequest request) {

        //判断用户有没有授权


        ReturnModel returnModel = new ReturnModel();
        try {
            WxMpUser origWxMpUser = this.wxMpService.getUserService().userInfo(openid, lang);
            returnModel.setDatum(origWxMpUser);
            modelMap.put("origWxMpUser", origWxMpUser);//openID用户

//            当前用户
            WxMpOAuth2AccessToken accessToken;
            accessToken = this.wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = this.wxMpService.getUserService().userInfo(accessToken.getOpenId(), lang);
            System.out.println("code="+code+"       "+ wxMpUser.toString());
            modelMap.put("wxMpUser",wxMpUser);//openID用户

            WxMpUser wxMpUser2 = this.wxMpService.oauth2getUserInfo(accessToken,lang);
            System.out.println("code="+code+"       "+ wxMpUser2.toString());
            modelMap.put("wxMpUser2",wxMpUser2);//openID用户

            String url = request.getScheme() +"://" + request.getServerName()
                    + (request.getServerPort()==80?"":(":" +request.getServerPort()))
                    + request.getServletPath();
            if (request.getQueryString() != null){
                url += "?" + request.getQueryString();
            }

            modelMap.put("url",url);
            WxJsapiSignature signature = wxMpService.createJsapiSignature(url);
            modelMap.put("signature",signature);

        } catch (WxErrorException e) {
            returnModel.setResult(false);
            returnModel.setReason(e.getError().toString());
            this.LOG.error(e.getError().toString());
        }
        return "getUserInfo";
    }

    /**
     * 通过code获得基本用户信息
     * 详情请见: http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
     *
     * @param response
     * @param code     code
     * @param lang     zh_CN, zh_TW, en
     */
    @RequestMapping(value = "getOAuth2UserInfo")
    public void getOAuth2UserInfo(HttpServletResponse response, @RequestParam(value = "code") String code, @RequestParam(value = "lang") String lang) {
        ReturnModel returnModel = new ReturnModel();
        WxMpOAuth2AccessToken accessToken;
        WxMpUser wxMpUser;
        try {
            accessToken = this.wxMpService.oauth2getAccessToken(code);
            wxMpUser = this.wxMpService.getUserService()
                .userInfo(accessToken.getOpenId(), lang);
            returnModel.setResult(true);
            returnModel.setDatum(wxMpUser);
            renderString(response, returnModel);
        } catch (WxErrorException e) {
            returnModel.setResult(false);
            returnModel.setReason(e.getError().toString());
            renderString(response, returnModel);
            this.LOG.error(e.getError().toString());
        }
    }

    /**
     * 用code换取oauth2的openid
     * 详情请见: http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html
     *
     * @param response
     * @param code     code
     */
    @RequestMapping(value = "getOpenid")
    public void getOpenid(HttpServletResponse response, @RequestParam(value = "code") String code) {
        ReturnModel returnModel = new ReturnModel();
        WxMpOAuth2AccessToken accessToken;
        try {
            accessToken = this.wxMpService.oauth2getAccessToken(code);
            returnModel.setResult(true);
            returnModel.setDatum(accessToken.getOpenId());
            renderString(response, returnModel);
        } catch (WxErrorException e) {
            returnModel.setResult(false);
            returnModel.setReason(e.getError().toString());
            renderString(response, returnModel);
            this.LOG.error(e.getError().toString());
        }
    }

    /**
     * 通过code获得基本用户信息
     * 详情请见: http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
     *
     * @param response
     * @param code     code
     */
    @RequestMapping(value = "/wx/getOAuth2User")
    @ResponseBody
    public Object getOAuth2UserJson(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "code") String code,@RequestParam(value = "callback") String callback) {
        ReturnModel returnModel = new ReturnModel();
        WxMpOAuth2AccessToken accessToken;
        WxMpUser wxMpUser = null;
        User user = (User)request.getSession().getAttribute("user");
        try {
            if(user == null) {
                accessToken = this.wxMpService.oauth2getAccessToken(code);
                wxMpUser = this.wxMpService.getUserService().userInfo(accessToken.getOpenId(), "zh_CN");
                if(wxMpUser!=null){
                    user = userService.getUserByOpenId(wxMpUser.getOpenId());
                }
            }
        } catch (WxErrorException e) {
            returnModel.setResult(false);
            returnModel.setReason(e.getError().toString());
            renderString(response, returnModel);
            this.LOG.error(e.getError().toString());
        }
        return JsonUtils.jsonp(user, callback);
        //return wxMpUser;
    }

    @Reference
    IWechatService wechatService;

    @RequestMapping(value = "/wx/template")
    @ResponseBody
    public Object sendTplMsg() throws WxErrorException {

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser("oXmgBszr7OI2yp2NOK7zKj69VgTo");
        templateMessage.setTemplateId("0uXEGx70J2EMZTcro0KkVZtL1N7Io5Rczgs9B_eKCYA");
        templateMessage.setUrl("http://m.andaxauto.com");
        templateMessage.setTopColor("#0033CC");

        templateMessage.getData().add(new WxMpTemplateData("first", "您收到一条车商报价信息，请查看", "#0033CC"));
        templateMessage.getData().add(new WxMpTemplateData("car", "2015款 奥迪A4L 1.6 自动时尚型","#0033CC"));
        templateMessage.getData().add(new WxMpTemplateData("dealer", "车王二手车超市","#0033CC"));
        templateMessage.getData().add(new WxMpTemplateData("price", "25.4万","#0033CC"));
        templateMessage.getData().add(new WxMpTemplateData("time", "2016-11-25 10:36","#0033CC"));

        String result = wechatService.sendTemplateMsg(templateMessage);

        JSONObject o = new JSONObject();
        o.put("content",templateMessage);
        o.put("result",result);

        return o;
    }

    /**
     * 通过session获得基本用户信息
     * 返回给页面 存到localstorage里面
     *
     * @param response
     */
    @RequestMapping(value = "/wx/getSessionUser")
    @ResponseBody
    public Object getSessionUser(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "callback") String callback) {
        User user = (User)request.getSession().getAttribute("user");
        if(user!=null) {
            LOG.info("user　session userId:"+user.getId() +"userNick:"+user.getNickName());
        }else{
            LOG.info("user　session 为空！");
        }
        return JsonUtils.jsonp(user, callback);
        //return wxMpUser;
    }



}
