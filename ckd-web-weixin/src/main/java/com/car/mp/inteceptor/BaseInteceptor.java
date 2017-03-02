package com.car.mp.inteceptor;

import com.car.mp.config.ApplicationConfig;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/9/20.
 */
public class BaseInteceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(BaseInteceptor.class);




    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("basePath", ApplicationConfig.DOMAIN); // 每个页面，都设置一下基础路径
        request.setAttribute("imgPath", ApplicationConfig.IMGPATH); // 图片服务器域名
        request.setAttribute("portal",ApplicationConfig.PORTAL);//jsonp请求的地址
        request.setAttribute("WEIXINURL",ApplicationConfig.WEIXINURL); //微信 url
        request.setAttribute("WECHATAPPID",ApplicationConfig.WECHATAPPID);//微信appid
        return true;
    }

    //after the handler is executed
    public void postHandle( HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView)
            throws Exception {
        // TODO Auto-generated method stub
    }

    public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }
}