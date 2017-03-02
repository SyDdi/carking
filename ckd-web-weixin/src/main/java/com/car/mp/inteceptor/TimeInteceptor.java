package com.car.mp.inteceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.User;
import com.car.service.IPeopleRelationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/9/20.
 */
public class TimeInteceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger("accesslog");

    @Reference
    IPeopleRelationService iPeopleRelationService;

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)
            throws Exception {
        try {
            //CarController 里面set 的session
            //用户没有session的时候 打开分享链接 不能及时 保存 分享人和被分享人关系 到这里等有session了 在做关系添加
            String  sharePeopleId = (String)request.getSession().getAttribute("sharePeopleId");
            User user = (User) request.getSession().getAttribute("user");
            if(sharePeopleId != null && !"".equals(sharePeopleId) && user!=null){
                iPeopleRelationService.saveFriend(sharePeopleId,user.getId());
            }
        }catch (Exception e){}

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    //after the handler is executed
    public void postHandle( HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        //modified the exisitng modelAndView
        //modelAndView.addObject("executeTime", executeTime);
        //log it
        //if (logger.isDebugEnabled()) {
            logger.info( request.getRequestURI() +"##"+StringUtils.defaultIfBlank(request.getQueryString(),"")+ "## executeTime : " + executeTime + "ms");
        //}
    }

    public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }
}