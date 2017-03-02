package com.car.mp.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.User;
import com.car.mp.domain.vo.ResponseVO;
import com.car.service.IUserService;
import com.google.gson.Gson;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 所有自定义Controller的顶级接口,封装常用的与session和response、request相关的操作
 * <p>
 * Created by FirenzesEagle on 2016/4/19 0019.
 */
public abstract class GenericController {


    @Autowired
    protected WxMpService wxMpService;

    @Reference
    IUserService userService;

    /**
     * 日志对象
     */
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * 默认每页数
     */
    protected final static int DEFAUTL_PAGE_SIZE = 10;

    /**
     * 客户端返回JSON字符串
     *
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, new Gson().toJson(object), "application/json");
    }

    /**
     * 输出JSON字符串
     *
     * @param vo
     */
    public void json(ResponseVO vo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            String jsonString = net.sf.json.JSONObject.fromObject(vo).toString();
            renderString(httpServletResponse, jsonString, "application/json");
        } catch (Exception e) {
            LOG.error("输出JSON出错." + e.getMessage());
        }
    }

    /**
     * 客户端返回字符串
     *
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            //解决跨域问题
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 从sesssion中获取用户信息，如果不存在，则从微信中获取
     * 将此处的从微信获取转移到拦截器中进行
     * @see com.car.mp.inteceptor.LoginInteceptor
     */
    protected User getUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
//        if (user == null) {
//            String code = request.getParameter("code");
//            if (StringUtils.isNotBlank(code)) {
//                try {
//                    WxMpOAuth2AccessToken accessToken = this.wxMpService.oauth2getAccessToken(code);
//                    String openId = accessToken.getOpenId();
//                    //从数据库中获取该用户的基本信息，如果没有，则创建
//                    User u = new User();u.setOpenId(openId);
//                    User us = userService.selectByKey(u);
//                    user = new User();
//                    user.setOpenId(openId);
//                    httpSession.setAttribute("user", user);
//                    this.LOG.info("取得微信用户的OPENID=" + openId);
//                } catch (WxErrorException e) {
//                    this.LOG.error(e.getError().toString());
//                }
//            }
//        }
//        if(user==null)
//            user=new User();
//        user.setId(1L);
        return user;
    }
}
