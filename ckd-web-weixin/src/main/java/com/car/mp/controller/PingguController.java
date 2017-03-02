package com.car.mp.controller;

import com.car.domain.User;
import com.car.mp.annotation.Frequency;
import com.car.mp.domain.*;
import com.car.mp.service.CarAreaService;
import com.car.mp.service.CarBrandService;
import com.car.mp.service.HistoryService;
import com.car.mp.util.HttpHandler;
import com.car.mp.util.IPUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@Controller
@RequestMapping("/pinggu")
public class PingguController extends GenericController{

	private static Logger logger = Logger.getLogger("PingguController");
    private final static String MONTH_FORMAT = "yyyy-MM";

	@Resource
	private CarBrandService carBrand;
    @Resource
    private CarAreaService carArea;
	@Resource
	private HistoryService historyService;

	private final static String URL_APPRAISE = "http://api.hangqingjia.com/service/getAppraise";
    private final static String URL_AGE_APPRAISE = "http://api.hangqingjia.com/service/getCarAgeAppraisal";

    @RequestMapping(value = "")
    public String index(HttpServletRequest request, ModelMap modelMap) {
//        doWeiXinLogin(request);
        User user = getUser(request);
        if(user!=null && user.getSubscribe() == 1){
            modelMap.put("isShow",1);
        }
        return "pinggu/index";
    }

    private void doWeiXinLogin(HttpServletRequest request){
        //获取用户session,旨在获取用户openId
        User user = getUser(request);
    }
	/**
	 * 估价
	 * 
	 * @return
	 */
	@RequestMapping(value = "/result.html")
    @Frequency(name="method", limit=10, time=1)
    public String search(@ModelAttribute("search") SearchEntity search, ModelMap modelMap,HttpServletRequest request) {
		String tip = "获取估价 | search | 参数：seach：" + search + " | ";
		HistoryEntity historyEntity =new HistoryEntity(search);
        User user = getUser(request);
        if(user!=null) {
            historyEntity.setOpenId(user.getOpenId());
            if(user.getSubscribe() == 1){
                modelMap.put("isShow",1);
            }
        }
		historyEntity.setIp(IPUtil.getIp(request));
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
					modelMap.put("brand", carBrand.getBrand(Integer.parseInt(brandId)));
					modelMap.put("factory", carBrand.getFactoryById(Integer.parseInt(factoryId)));
					SeriesEntity series = new SeriesEntity();
					series.setSeriesIds(seriesId);
					series.setSeriesName(seriesName);
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
				
				logger.debug(tip + "准备请求第三方接口，URL:" + URL_APPRAISE + "，参数：" + map);
				String html = HttpHandler.Post(URL_APPRAISE, map, 200);
				logger.debug(tip + "准备请求第三方接口返回结果：" + html);
				ResultEntity result = toResultObject(html);
				if (1 == result.getStatus()) {
					modelMap.put("result", result);
					historyEntity.addResultEntity(result);
				}else{
                    return "pinggu/noresult";
                }
                //请求不同车龄的价格接口
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("modelId", search.getModelId());
                map2.put("reg_date", search.getTimeId());
                map2.put("colorId", search.getColorId());
                map2.put("mile_age", search.getMile());
                map2.put("zoneId", search.getCityId());
                String fromDate = DateFormatUtils.format(new Date(), MONTH_FORMAT);
                String nextDate = DateFormatUtils.format(DateUtils.addMonths(new Date(),30), MONTH_FORMAT);
                map2.put("carAgeScope",fromDate+","+nextDate);
                map2.put("carAgeScopeInterval",6);
                logger.debug(tip + "准备请求第三方接口，URL:" + URL_AGE_APPRAISE + "，参数：" + map2);
                String html2 = HttpHandler.Post(URL_AGE_APPRAISE, map2, 200);
                logger.debug(tip + "准备请求第三方接口返回结果：" + html2);
                CarAgeAppraisalEntity carAgeAppraisalEntity = toCarAgeAppraisalObject(html2);
                if (1 == carAgeAppraisalEntity.getStatus()) {
                    modelMap.put("ageAppraise", carAgeAppraisalEntity);
                }else{
                    return "pinggu/noresult";
                }
			}
		} catch (Exception e) {
			logger.debug(tip + "异常：" + e.getMessage());
		}  finally {
			try {
				historyService.saveOperationRecorder(historyEntity);
			}   catch (Exception e){
				logger.debug("保存估值记录失败");
				e.printStackTrace();
			}
		}
		return "pinggu/result";
	}
    /**
     * 价格分解计算器
     */
    @RequestMapping(value = "/calc.html", method = RequestMethod.GET)
    public String priceCalc(@RequestParam("modelId") String modelId,@RequestParam("timeId") String timeId,@RequestParam("colorId") int colorId,
                            @RequestParam("mile") String mile,@RequestParam("zoneId") int zoneId,@RequestParam("cond") String cond,
                            ModelMap modelMap,HttpServletRequest request) {
        String tip = "获取估价明细 |  ";
        try {
            logger.debug(tip + "开始...");
            //取得品牌信息
            ModelDetailEntity modelDetail = carBrand.getModelById(modelId);
            modelMap.put("model",modelDetail);
            //取得地区信息
            CarAreaEntity area = carArea.get(zoneId);
            modelMap.put("area",area.getCarAreaName());
            //取得颜色信息
            modelMap.put("color",getColor(colorId));
            modelMap.put("timeId", timeId);
            modelMap.put("mile", mile);

            // 请求接口
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("modelId", modelId);
            map.put("reg_date", timeId);
            map.put("colorId", colorId);
            map.put("mile_age", mile);
            map.put("zoneId", zoneId);
            map.put("detail",1);

            logger.debug(tip + "准备请求第三方接口，URL:" + URL_APPRAISE + "，参数：" + map);
            String html = HttpHandler.Post(URL_APPRAISE, map, 200);
            logger.debug(tip + "准备请求第三方接口返回结果：" + html);
            ResultEntity result = toResultObject(html);

            if (1 == result.getStatus()) {
                modelMap.put("result", result);
                //判断车况选择对应车况数据
                if(cond.equals("worse")){
                	modelMap.put("purchasePriceDifference", result.getWorsePurchasePriceDifference());
                	modelMap.put("retailPrice", result.getWorseRetailPrice());
                	modelMap.put("cond", "车况一般");
                }else if(cond.equals("ordinary")){
                	modelMap.put("purchasePriceDifference", result.getPurchasePriceDifference());
                	modelMap.put("retailPrice", result.getRetailPrice());
                	modelMap.put("cond", "车况良好");
                }else{
                	modelMap.put("purchasePriceDifference", result.getBetterPurchasePriceDifference());
                	modelMap.put("retailPrice", result.getBetterRetailPrice());
                	modelMap.put("cond", "车况优秀");
                }
            }
            
        } catch (Exception e) {
            logger.debug(tip + "异常：" + e.getMessage());
        }
        return "pinggu/calc";
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

    private String getColor(int ColorId){
        switch(ColorId){
            case 1:return "白色";
            case 2:return "黑色";
            case 3:return "咖啡色";
            case 4:return "橙色";
            case 5:return "灰色";
            case 6:return "紫色";
            case 7:return "红色";
            case 8:return "绿色";
            case 9:return "蓝色";
            case 10:return "银色";
            case 11:return "香槟色";
            case 12:return "黄色";
            case 13:return "多彩色";
            default:return "其他";
        }
    }
}