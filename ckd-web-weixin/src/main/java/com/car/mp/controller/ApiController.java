package com.car.mp.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.car.mp.service.CarBrandService;
import com.car.mp.util.HttpHandler;

@Controller
@RequestMapping("/api")
public class ApiController {
	private static Logger logger = Logger.getLogger("api");

	@Resource
	private CarBrandService carBrand;
	/**
	 * 获取热门车型列表
	 * @return
	 */
	@RequestMapping(value = "/getHotCarList", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public  String getHotCarList(HttpServletRequest request) {
		try {
			JSONObject obj = new JSONObject();
		    obj.put("status", 0);
		    obj.put("message", "成功");
		    obj.put("data", JSONArray.fromObject(carBrand.getHotCarList()));
		    return obj.toString();
		} catch(Exception e){
			logger.info(e.getMessage());
			return "";
		} 
	}
	
	/**
	 * 获取车型列表
	 * @return
	 */
	@RequestMapping(value = "/getAllCarList", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public  String getAllCarList(HttpServletRequest request) {
		try {
			JSONObject obj = new JSONObject();
			obj.put("status", 0);
			obj.put("message", "成功");
			obj.put("data", JSONArray.fromObject(carBrand.getAllCarList()));
			return obj.toString();
		} catch(Exception e){
			logger.info(e.getMessage());
			return "";
		} 
	}
	
	/**
	 * 获取地域列表
	 * @return
	 */
	@RequestMapping(value = "/getZone", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public  String getZone(HttpServletRequest request) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String result = HttpHandler.Get("http://api.hangqingjia.com/service/getZone", params, 3); 
			return result;
		} catch(Exception e){
			logger.info(e.getMessage());
			return "";
		} 
	}
}
