package com.car.web.admin.core.freemarker.extend;

import com.car.web.admin.core.shiro.token.manager.TokenManager;
import com.car.web.admin.core.statics.Constant;
import com.car.web.admin.domain.UUser;
import com.car.web.admin.utils.LoggerUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
public class FreeMarkerViewExtend extends FreeMarkerView {
	
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request){
		
		try {
			super.exposeHelpers(model, request);
		} catch (Exception e) {
			LoggerUtils.fmtError(FreeMarkerViewExtend.class,e, "FreeMarkerViewExtend 加载父类出现异常。请检查。");
		}
		model.put(Constant.CONTEXT_PATH, request.getContextPath());
		model.putAll(Ferrmarker.initMap);
		UUser token = TokenManager.getToken();
		//String ip = IPUtils.getIP(request);
		model.put("token", token);//登录的token
		model.put("_time", new Date().getTime());
		model.put("NOW_YEAY", Constant.NOW_YEAY);//今年
		
		model.put("_v", Constant.VERSION);//版本号，重启的时间
		model.put("cdn", Constant.DOMAIN_CDN);//CDN域名
		model.put("domainImg",Constant.DOMAIN_IMG);//图片域名
		model.put("basePath", request.getContextPath());//base目录。
		model.put("weixinURL", Constant.weixinURL);//微信域名
	}
}
