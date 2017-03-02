package com.car.mp.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2016/9/22.
 */
public class IPUtil {

    /**
     * 从head中获取客户IP地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String forwardedIp = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotBlank (forwardedIp) && forwardedIp.split(",").length>0){
            return forwardedIp.split(",")[0].trim();
        }else{
            forwardedIp = request.getHeader("Cdn-Src-Ip");
            if(StringUtils.isNotBlank(forwardedIp)){
                return forwardedIp;
            }
        }
        return request.getRemoteAddr();
    }


}
