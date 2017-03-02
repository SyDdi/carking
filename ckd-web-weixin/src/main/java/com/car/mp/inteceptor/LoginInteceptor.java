package com.car.mp.inteceptor;

import com.car.domain.User;
import com.car.mp.service.UserService;
import com.car.mp.util.CommonUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/20.
 */
public class LoginInteceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(LoginInteceptor.class);
    @Autowired
    protected WxMpService wxMpService;

    @Autowired
    UserService userService;

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        //如果Session中存在，直接返回下一页
//        if(user!=null){
//            return true;
//        }else{
            String code = request.getParameter("code");
            if (StringUtils.isNotBlank(code)) {
                try {
                    //取得用户信息
                    WxMpOAuth2AccessToken accessToken = this.wxMpService.oauth2getAccessToken(code);
                    String openId = accessToken.getOpenId();
                    WxMpUser wxMpUser = null;
                    try {
                        wxMpUser = this.wxMpService.getUserService().userInfo(accessToken.getOpenId(), "zh_CN");
                    } catch (WxErrorException e) {
                        logger.error(e.getError().toString());
                    }

//                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//                    this.logger.info("\n用户ID="+ Helper.getIP(request));

                    //将最新的用户信息同步到数据库，
                    User u = userService.getUserByOpenId(openId);
                    if(u==null){
                        u = new User();
                        u.setOpenId(openId);
                        u.setCreated(new Date());
                        if(wxMpUser!=null){
                            u.setNickName(CommonUtil.filterEmoji(wxMpUser.getNickname()));
                            u.setHeadImgUrl(wxMpUser.getHeadImgUrl());
                            u.setSubscribe(wxMpUser.getSubscribe()?1:0);
                        }
                        u = userService.createUser(u);
                    }else if(wxMpUser!=null){
                        u.setNickName(CommonUtil.filterEmoji(wxMpUser.getNickname()));
                        u.setHeadImgUrl(wxMpUser.getHeadImgUrl());
                        u.setSubscribe(wxMpUser.getSubscribe()?1:0);
                        userService.updateUser(u);
                    }
                    httpSession.setAttribute("user", u);
                } catch (WxErrorException e) {
                    logger.error(e.getError().toString());
                }
            }else{//否则，跳转到网页授权页面
//                String url  =wxMpService.oauth2buildAuthorizationUrl(Servlets.getFullUrl(request),"snsapi_base", "");
//                response.sendRedirect(url);

                  //这里就不跳转了，会引起非微信客户端访问的提醒，需要授权的直接使用javascript进行跳转
                  return true;
            }
//        }
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