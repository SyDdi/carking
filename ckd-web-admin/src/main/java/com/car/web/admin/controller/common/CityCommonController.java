package com.car.web.admin.controller.common;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.car.domain.Province;
import com.car.domain.Zone;
import com.car.service.IBrandService;
import com.car.service.IProvinceService;
import com.car.service.IZoneService;
import com.car.web.admin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/portal/admin/city")
public class CityCommonController extends BaseController {


	@Reference
	IZoneService zoneService;
	@Reference
	IProvinceService provinceService;

	/*
    获取品牌Json数据
     */
	@RequestMapping(value = "/province")
	@ResponseBody
	public Object provinceList() {
		return provinceService.provinceList();
	}

	@RequestMapping(value = "/zoneList")
	@ResponseBody
	public Object zoneList(@RequestParam("provinceId") Integer provinceId) {
		return zoneService.zoneList(provinceId);
	}

}



