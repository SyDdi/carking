package com.car.web.admin.controller.common;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.car.Page;
import com.car.core.utils.FileUtils;
import com.car.domain.*;
import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.exception.CommonException;
import com.car.service.*;
import com.car.web.admin.controller.BaseController;
import com.car.web.admin.core.shiro.token.manager.TokenManager;
import com.car.web.admin.domain.UUser;
import com.car.web.admin.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/portal/admin/model")
public class BrandCommonController extends BaseController {

	@Reference
	IBrandService brandService;

	/*
    获取品牌Json数据
     */
	@RequestMapping(value = "/brand")
	@ResponseBody
	public Object brandList() {
		Object result = brandService.brandList();
		return result;
	}

	@RequestMapping(value = "/family")
	@ResponseBody
	public Object familyList(@RequestParam("brandId") Integer brandId) {
		Object result = brandService.familyList(brandId);
		return result;
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object modelList(@RequestParam("brandId") Integer brandId,@RequestParam("factoryId") Integer factoryId,@RequestParam("seriesIds") String seriesIds) {
		Object result = brandService.modelList(brandId,factoryId,seriesIds);
		return result;
	}

}



