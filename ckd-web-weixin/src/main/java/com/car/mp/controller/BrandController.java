package com.car.mp.controller;

import com.car.mp.domain.*;
import com.car.mp.service.CarBrandService;
import com.car.mp.service.HistoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.*;

/**
 * 选择品牌
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
@Controller
@RequestMapping("/brand")
public class BrandController extends GenericController{

	private static Logger logger = Logger.getLogger("api");

	@Resource
	private CarBrandService carBrand;
	@Resource
	private HistoryService historyService;

	private final static String URL_APPRAISE = "http://api.hangqingjia.com/service/getAppraise";

	/**
	 * 获取车型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/brand_list.html", method = RequestMethod.GET)
	public String carList(ModelMap modelMap) {
		String tip = "获取车型列表 | carList | ";
		try {
			logger.debug(tip + "开始...");
			LinkedHashMap<String, List<CarBrand>> list = carBrand.getAllCarList();
			if (null != list) {
				logger.debug(tip + "返回记录数：" + list.size());
			}
			modelMap.put("list", list);
		} catch (Exception e) {
			logger.debug(tip + "异常：" + e.getMessage());
		}
		return "brand/list";
	}

	/**
	 * 获取厂商列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/factory_{brandId:[\\d]+}_{factoryId:[\\d]+}.html", method = RequestMethod.GET)
	public String factoryList(@PathVariable Integer brandId, @PathVariable Integer factoryId, ModelMap modelMap) {
		try {
			List<CarFactoryEntity> list = carBrand.getFactoryList(brandId);
			if (0 == factoryId) {
				if (CollectionUtils.isNotEmpty(list)) {
					factoryId = list.get(0).getFactoryId();
				}
			}
			if (0 < factoryId) {
				SeriesGroupEntity seriesGroupEntity = carBrand.getSeriesGroup(brandId, factoryId);
				if (1 == seriesGroupEntity.getStatus() && CollectionUtils.isNotEmpty(seriesGroupEntity.getData())) {
					logger.debug( "series count：" + seriesGroupEntity.getData().size());
					modelMap.put("data", seriesGroupEntity.getData());
				}
			}
			if (null != list) {
				logger.debug( "返回[厂商]记录数：" + list.size());
			}
			modelMap.put("list", list);
		} catch (Exception e) {
			logger.error("异常：" + e.getMessage());
		}
		modelMap.put("factoryId", factoryId);
		modelMap.put("brandId", brandId);
		return "brand/factory";
	}

	/**
	 * 获取车型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/model_{brandId:[\\d]+}_{factoryId:[\\d]+}_{seriesId:[\\d\\x2C]+}_{year:[\\d]+}.html", method = RequestMethod.GET)
	public String modelList(@PathVariable Integer brandId, @PathVariable Integer factoryId,
			@PathVariable String seriesId, @PathVariable Integer year, ModelMap modelMap) {
		String tip = "获取车型列表 | factoryList | 参数：brandId：" + brandId + ", factoryId：" + factoryId + ",seriesId="
				+ seriesId;
		try {
			logger.debug(tip + "开始...");
			List<CarFactoryEntity> list = carBrand.getFactoryList(brandId);
			if (0 == factoryId) {
				if (CollectionUtils.isNotEmpty(list)) {
					factoryId = list.get(0).getFactoryId();
				}
			}
			if (0 < factoryId) {
				// 请求接口
				ModelListEntity modelListEntity = carBrand.getModelListEntity(brandId, factoryId, seriesId);
				if (1 == modelListEntity.getStatus() && CollectionUtils.isNotEmpty(modelListEntity.getData())) {
					logger.debug(tip + "data count：" + modelListEntity.getData().size());
					List<ModelEntity> data = modelListEntity.getData();
					if (CollectionUtils.isNotEmpty(data)) {
						List<String> yearData = new ArrayList<String>();
						List<ModelEntity> modelData = new ArrayList<ModelEntity>();
						for (int i = 0; i < data.size(); i++) {
							String cyear = data.get(i).getMakeYear();
							if (!yearData.contains(cyear)) {
								yearData.add(cyear);
							}
						}

						Collections.sort(yearData, new Comparator<String>() {
							public int compare(String arg0, String arg1) {
								return arg1.compareTo(arg0);
							}
						});

						if (0 == year && CollectionUtils.isNotEmpty(yearData)) {
							year = Integer.parseInt(yearData.get(0));
						}
						for (int i = 0; i < data.size(); i++) {
							String cyear = data.get(i).getMakeYear();
							if (year.toString().equals(cyear)) {
								modelData.add(data.get(i));
							}
						}
						modelMap.put("yearData", yearData);
						modelMap.put("modelData", modelData);
						modelMap.put("brand",carBrand.getBrand(brandId));
					}
				}
			}

			if (null != list) {
				logger.debug(tip + "返回记录数：" + list.size());
			}
			modelMap.put("list", list);
		} catch (Exception e) {
			logger.debug(tip + "异常：" + e.getMessage());

		}
		modelMap.put("year", year);
		modelMap.put("factoryId", factoryId);
		modelMap.put("brandId", brandId);
		modelMap.put("seriesId", seriesId);
		return "brand/model";
	}

}
