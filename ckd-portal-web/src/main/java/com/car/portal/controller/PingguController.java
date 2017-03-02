package com.car.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.portal.dto.CarAgeAppraisalEntity;
import com.car.portal.dto.ResultEntity;
import com.car.portal.dto.SearchEntity;
import com.car.portal.utils.HttpHandler;
import com.car.portal.utils.IConfig;
import com.car.portal.utils.RestResultGenerator;
import com.car.service.IBrandService;
import com.car.service.ICarFactoryService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 选择品牌
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
@RestController
@RequestMapping("/api/pinggu")
public class PingguController {

	private static Logger logger = Logger.getLogger("PingguController");
    private final static String MONTH_FORMAT = "yyyy-MM";

    @Reference
    private IBrandService brandService;
	@Reference
	private ICarFactoryService factoryService;

    private final static String URL_APPRAISE = "/service/getAppraise";
    private final static String URL_AGE_APPRAISE = "/service/getCarAgeAppraisal";

	/**
	 * 估价
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search")
    public Object search(@ModelAttribute("search") SearchEntity search) {
		String tip = "获取估价 | search | 参数：seach：" + search + " | ";
		Map modelMap = new HashMap();
		try {
			logger.debug(tip + "开始...");
			if (null != search) {
				String[] pinPaiParam = search.getModelId().split("_");
				if(pinPaiParam.length==6){
					String brandId = pinPaiParam[0];
					String factoryId = pinPaiParam[1];
					String seriesName = pinPaiParam[2];
					String seriesId = pinPaiParam[3];
					String year = pinPaiParam[4];
					search.setModelId(pinPaiParam[5]);
// 					modelMap.put("brand", brandService.selectByBrandId(Long.parseLong(brandId)));
//					modelMap.put("factory", brandService.getFactoryById(Integer.parseInt(factoryId)));
					Map series = new HashMap();
					series.put("seriesIds",seriesId);
					series.put("seriesName",seriesName);
					modelMap.put("series",series);
                    modelMap.put("brandId",brandId);
                    modelMap.put("factoryId",factoryId);

				}
                modelMap.put("search", search);
				// 请求接口
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("modelId", search.getModelId());
				map.put("reg_date", search.getTimeId());
				map.put("colorId", search.getColorId());
				map.put("mile_age", search.getMile());
				map.put("zoneId", search.getCityId());
				map.put("detail",1);
				
				logger.debug(tip + "准备请求第三方接口，URL:" + IConfig.get("URL_APPRAISE")+ URL_APPRAISE + "，参数：" + map);
				String html = HttpHandler.Post(IConfig.get("URL_APPRAISE")+URL_APPRAISE, map, 200);
				logger.debug(tip + "准备请求第三方接口返回结果：" + html);
				ResultEntity result = toResultObject(html);
				JSONObject obj = JSONObject.fromObject(html);
				if (1 == result.getStatus()) {
					modelMap.put("result", obj);
				}else{
                    return RestResultGenerator.genResult(modelMap.toString(), "无数据!");
                }
				if(search.getFlag()!=null) {//
					//请求不同车龄的价格接口
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("modelId", search.getModelId());
					map2.put("reg_date", search.getTimeId());
					map2.put("colorId", search.getColorId());
					map2.put("mile_age", search.getMile());
					map2.put("zoneId", search.getCityId());
					String fromDate = DateFormatUtils.format(new Date(), MONTH_FORMAT);
					String nextDate = DateFormatUtils.format(DateUtils.addMonths(new Date(), 30), MONTH_FORMAT);
					map2.put("carAgeScope", fromDate + "," + nextDate);
					map2.put("carAgeScopeInterval", 6);
					logger.debug(tip + "准备请求第三方接口，URL:" + IConfig.get("URL_APPRAISE")+URL_AGE_APPRAISE + "，参数：" + map2);
					String html2 = HttpHandler.Post(IConfig.get("URL_APPRAISE")+URL_AGE_APPRAISE, map2, 200);
					logger.debug(tip + "准备请求第三方接口返回结果：" + html2);
					CarAgeAppraisalEntity carAgeAppraisalEntity = toCarAgeAppraisalObject(html2);
					JSONObject obj1 = JSONObject.fromObject(html2);
					if (1 == carAgeAppraisalEntity.getStatus()) {
						modelMap.put("ageAppraise", obj1.toString());
					} else {
						return RestResultGenerator.genResult(modelMap.toString(), "无数据!");
					}
				}
			}
		} catch (Exception e) {
			logger.debug(tip + "异常：" + e.getMessage());
			return RestResultGenerator.genErrorResult(tip + "异常");
		}  finally {

		}
		return RestResultGenerator.genResult(JSONObject.fromObject(modelMap), "成功!");
	}


	/**
	 * json 转 对象
	 * 
	 * @param json
	 * @return
	 */
	private ResultEntity toResultObject(String json) {
		ResultEntity result = new ResultEntity();
		JSONObject obj = JSONObject.fromObject(json);
		result.setStatus(obj.optInt("status"));
		result.setMessge(obj.optString("message"));
		if (1 == result.getStatus()) {
			JSONObject price = obj.optJSONObject("data");
			if (null != price) {
				result.setNewPriceW(price.optString("newPriceW"));
				result.setNewcarUpdTaxPrice(price.optString("newcarUpdTaxPrice"));
				result.setSaleStatus(price.optString("saleStatus"));
				result.setFileStatus(price.optString("fileStatus"));
				result.setEasyToSell(price.optString("easyToSell"));
				result.setBetterRetailPrice(price.optString("betterRetailPrice"));
				result.setBetterPersonalTradingPrice(price.optString("betterPersonalTradingPrice"));
				result.setBetterPurchasePrice(price.optString("betterPurchasePrice"));
				result.setRetailPrice(price.optString("retailPrice"));
				result.setPersonalTradingPrice(price.optString("personalTradingPrice"));
				result.setPurchasePrice(price.optString("purchasePrice"));
				result.setWorseRetailPrice(price.optString("worseRetailPrice"));
				result.setWorsePersonalTradingPrice(price.optString("worsePersonalTradingPrice"));
				result.setWorsePurchasePrice(price.optString("worsePurchasePrice"));
                result.setBasePrice(price.optString("basePrice"));
                result.setMilePrice(price.optInt("milePrice"));
                result.setColorPrice(price.optInt("colorPrice"));
                result.setZonePrice(price.optInt("zonePrice"));
                result.setBetterPurchasePriceDifference(price.optInt("betterPurchasePriceDifference"));
                result.setPurchasePriceDifference(price.optInt("purchasePriceDifference"));
                result.setWorsePurchasePriceDifference(price.optInt("worsePurchasePriceDifference"));
			}
		}
		return result;
	}


    /**
     * 转化价格区间的价格
     */
    private CarAgeAppraisalEntity toCarAgeAppraisalObject(String json) {
        CarAgeAppraisalEntity result = new CarAgeAppraisalEntity();
        JSONObject obj = JSONObject.fromObject(json);
        result.setStatus(obj.optInt("status"));
        result.setMessage(obj.optString("message"));
        if (1 == result.getStatus()) {
            JSONObject price = obj.optJSONObject("data");
            if (null != price) {
                result.setNewPriceW(price.optString("newPriceW"));
                result.setNewcarUpdTaxPrice(price.optString("newcarUpdTaxPrice"));
                result.setSaleStatus(price.optString("saleStatus"));
                result.setFileStatus(price.optString("fileStatus"));
                result.setEasyToSell(price.optString("easyToSell"));
                result.setPriceList(price.optJSONArray("priceList"));
            }
        }
        return result;
    }
}