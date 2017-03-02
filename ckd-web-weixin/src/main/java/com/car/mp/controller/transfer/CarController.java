package com.car.mp.controller.transfer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.core.web.Servlets;
import com.car.domain.*;
import com.car.mp.controller.GenericController;
import com.car.mp.service.UserService;
import com.car.security.HashIdsHelper;
import com.car.service.*;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 转让
 * @author admin
 */
@Controller
@RequestMapping("/car")
public class CarController extends GenericController{
	@Reference
	IVehicleService vehicleService;//调用Dubbo暴露的接口
	@Autowired
	UserService userService;
	@Reference
	IZoneService iZoneService;
	@Reference
	IModelService iModelService;
	@Reference
	ICollectionService iCollectionService;
	@Reference
	IUserService iUserService;
	@Reference
	IPeopleRelationService iPeopleRelationService;

	/**
	 * 车源详情页（卖主页面）
	 * @param modelMap
	 * @param HashvhclId 请使用 HashIdsHelper.encode 加密
     * @return
     */
	@RequestMapping("/{id}.html")
	public String myCarInfo(ModelMap modelMap, @PathVariable("id") String HashvhclId, String source , HttpServletRequest request, HttpServletResponse response){

		Long vhclId = HashIdsHelper.decode(HashvhclId,-1) ;
		if(vhclId == null || vhclId < 0){
			return "transfer/deletepage";
		}
		Vehicle vehicle = vehicleService.selectByKey(vhclId);
		if(vehicle == null) {
			return "transfer/deletepage";
		}
		if (vehicle.getIsDelete() == 1) { //当车辆状态为删除的时候 跳转到空白提示页  车源已删除
			return "transfer/deletepage";
		}

		//用户信息
		User user = getUser(request);
		String share = "";//分享人id
		//分享签名
		if(user != null ){
			share = HashIdsHelper.encode(user.getId());
		}else{
			request.getSession().setAttribute("sharePeopleId",source);
		}
		String urlNotParam = request.getScheme() +"://" + request.getServerName()
				+ (request.getServerPort()==80?"":(":" +request.getServerPort()))
				+ request.getServletPath();
		String url = Servlets.replaceParam( urlNotParam ,"source",share);
		try {
		//rul中没有source 的时候 分享签名会不通过（ 签名需要分享的链接和当前地址栏的链接一致 ） so 这里重定向到有source的链接
			if(Servlets.getFullUrl(request).indexOf("source=") < 0 || Servlets.getFullUrl(request).indexOf("&") > 1){
				return "redirect:"+url;
			}
			modelMap.put("url", url);
			WxJsapiSignature signature = null;
			signature = wxMpService.createJsapiSignature(url);
			modelMap.put("signature", signature);
			LOG.warn(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			LOG.info("user　session userId:" + user.getId() + "userNick:" + user.getNickName());
			if(user.getSubscribe() == 1){
				modelMap.put("subscribe",1);
			}

			//分享之后 会有source 认定打开连接的人 和 分享的人是朋友 做双向好友添加
			iPeopleRelationService.saveFriend(source,user.getId());
			//添加收藏判断
			Collection collect = new Collection();
			collect.setUserId(user.getId());
			collect.setVehicleId(vhclId);
			Short collStatus = 0;
			List<Collection> collList = iCollectionService.select(collect);
			if (collList.size() > 0) {
				collStatus = collList.get(0).getStatus();
			}
			modelMap.put("userId", user.getId());
			modelMap.put("collStatus", collStatus);
			if (user.getId().equals(vehicle.getUserId()) || user.getId() == vehicle.getUserId()) {
				modelMap.put("isSave", 1);
			}
		}

		Model model = iModelService.selectByKey(Integer.valueOf(vehicle.getModelId().toString()));//由于重载，要转成Integer类型
		Zone regZone = iZoneService.selectByKey(vehicle.getRegZoneId());
		Zone zone = iZoneService.selectByKey(vehicle.getZoneId());

		modelMap.put("model", model);
		modelMap.put("vehicle", vehicle);
		modelMap.put("publicDate", vehicle.getPublicDate() != null ? new SimpleDateFormat("YYYY-MM-dd").format(vehicle.getPublicDate()) : "--");
		modelMap.put("purchasePrice", new DecimalFormat("######.00").format((vehicle.getExpectPrice() != null ? (vehicle.getExpectPrice() / 10000.00) : 0)));
		modelMap.put("regZone", regZone != null ? regZone.getZoneName() : "");
		modelMap.put("zoneName", zone != null ? zone.getZoneName() : "");

		User vehicleUser = iUserService.selectByKey(vehicle.getUserId());
		if (vehicleUser != null) {
			User showUser = new User();
			showUser.setHeadImgUrl(vehicleUser.getHeadImgUrl());
			showUser.setNickName(vehicleUser.getNickName());
			modelMap.put("showUser", showUser);
		}
		return "transfer/seller";
	}

}
