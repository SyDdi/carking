package com.car.web.admin.utils;

import com.car.web.admin.core.config.IConfig;
import me.chanjar.weixin.common.util.http.URIUtil;

/**
 * 微信util
 * Created by shenyong on 2016/12/15.
 */
public class WeiXinUtil {

    /**
     * 拼接微信url
     * @param redirectURI
     * @param scope
     * @param state
     * @return
     */
    public static String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
        StringBuilder url = new StringBuilder();
        url.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
        url.append("appid=").append(IConfig.get("WECHATAPPID"));
        url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectURI));
        url.append("&response_type=code");
        url.append("&scope=").append(scope);
        if(state != null) {
            url.append("&state=").append(state);
        }
        url.append("#wechat_redirect");
        return url.toString();
    }
}
