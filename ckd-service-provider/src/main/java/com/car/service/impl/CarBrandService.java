package com.car.service.impl;

import com.car.domain.CarBrand;
import com.car.domain.CarModel;
import com.car.domain.Series;
import com.car.domain.SeriesGroup;
import com.car.repository.base.CarBrandMapper;
import com.car.service.ICarBrandService;
import com.car.utils.HttpHandler;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("ICarBrandService")
public class CarBrandService extends BaseService<CarBrand> implements ICarBrandService {

    @Autowired
    private CarBrandMapper carBrandMapper;

    private static Logger logger = Logger.getLogger("CarBrandService");

    private final static String URL_SERIES_GROUP = "http://api.hangqingjia.com/service/getSeriesGroup";
    private final static String URL_MODEL = "http://api.hangqingjia.com/service/getModel";
    private final static String URL_MODEL_BYID = "http://api.hangqingjia.com/service/getModelById";

    /**
     * 从第三方API获取厂商及车系列表
     * key格式为品牌ID_厂商ID
     */
    LoadingCache<String ,SeriesGroup> seriesCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<String, SeriesGroup>() {
                public SeriesGroup load(String key) throws Exception {
                    String[] keys =  StringUtils.split(key, "_");
                    String tip = "获取厂商列表 | factoryList | 参数：brandId：" + keys[0] + ", factoryId：" + keys[1];
                    // 请求接口
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("brandId", keys[0]);
                    map.put("makeId", keys[1]);
                    logger.debug(tip + "准备请求第三方接口，URL:" + URL_SERIES_GROUP + "，参数：" + map);
                    String html = HttpHandler.Get(URL_SERIES_GROUP, map, 200);
                    logger.debug(tip + "准备请求第三方接口返回结果：" + html);
                    SeriesGroup seriesGroup = toSeriesGroup(html);
                    return seriesGroup;
                }
            });

    @Override
    public SeriesGroup getSeriesGroup(int brandId,int factoryId){
        SeriesGroup seriesGroup = null;
        try{ seriesGroup = seriesCache.get(brandId+"_"+factoryId); }catch (Exception e) { e.printStackTrace();}
        return seriesGroup;
    }

    /**
     * 从第三方API获取年款和车型列表
     * key格式为品牌ID_厂商ID
     */
    LoadingCache<String ,List<CarModel>> modelListCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<String,List<CarModel>>() {
                public List<CarModel> load(String key) throws Exception {
                    String[] keys =  org.apache.commons.lang3.StringUtils.split(key, "_");
                    String tip = "获取车型列表 | factoryList | 参数：brandId：" + keys[0] + ", factoryId：" + keys[1] + ",seriesId： "+keys[2];
                    // 请求接口
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("brandId", keys[0]);
                    map.put("makeId", keys[1]);
                    map.put("seriesIds", keys[2]);
                    logger.debug(tip + "准备请求第三方接口，URL:" + URL_MODEL + "，参数：" + map);
                    String html = HttpHandler.Get(URL_MODEL, map, 200);
                    logger.debug(tip + "准备请求第三方接口返回结果：" + html);
                    List<CarModel> modelList = toModelList(html);
                    return modelList;
                }
            });

    @Override
    public List<CarModel> getModelList(int brandId,int factoryId,String seriesId){
        List<CarModel> modelList = null;
        try{ modelList = modelListCache.get(brandId+"_"+factoryId+"_"+seriesId); }catch (Exception e) { }
        return modelList;
    }

    /**
     * 根据车型ID获取车型并缓存
     */

    LoadingCache<Integer ,CarModel> carModelCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<Integer, CarModel>() {
                public CarModel load(Integer key) throws Exception {
                    String tip = "获取车型| getModel | 参数：modelId：" +key;
                    // 请求接口
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("modelId", key);
                    logger.debug(tip + "准备请求第三方接口，URL:" + URL_MODEL_BYID + "，参数：" + map);
                    String html = HttpHandler.Get(URL_MODEL_BYID, map, 200);
                    logger.debug(tip + "准备请求第三方接口返回结果：" + html);
                    CarModel carModel = toModelObject(html);
                    return carModel;
                }
            });

    @Override
    public CarModel getModelById(int modelId){
        CarModel carModel=null;
        try{
            carModel=carModelCache.get(modelId);
        }catch(Exception e){
        }
        return carModel;
    }

    @Override
    public CarBrand findBrandByBrandId(Integer brandId) {
        CarBrand carBrand = new CarBrand();
        carBrand.setBrandId(brandId);
        return carBrandMapper.selectOne(carBrand);
    }

    /**
     * json 转 对象
     *
     * @param json
     * @return
     */
    private SeriesGroup toSeriesGroup(String json) {
        SeriesGroup seriesGroup = new SeriesGroup();
        JSONObject obj = JSONObject.fromObject(json);
        seriesGroup.setStatus(obj.optInt("status"));
        seriesGroup.setMessge(obj.optString("message"));
        if (1 == seriesGroup.getStatus()) {
            JSONArray arrays = obj.optJSONArray("data");
            if (null != arrays) {
                List<Series> list = new ArrayList<Series>();
                for (int i = 0; i < arrays.size(); i++) {
                    Series entity = new Series();
                    JSONObject item = arrays.getJSONObject(i);
                    entity.setSeriesIds(item.optString("seriesIds"));
                    entity.setSeriesName(item.optString("seriesName"));
                    list.add(entity);
                }
                seriesGroup.setData(list);
            }
        }
        return seriesGroup;
    }


    /**
     * json 转 对象
     *
     * @param json
     * @return
     */
    private List<CarModel> toModelList(String json) {
        List<CarModel> list = new ArrayList<CarModel>();
        JSONObject obj = JSONObject.fromObject(json);
        if (1 == obj.optInt("status")) {
            JSONArray arrays = obj.optJSONArray("data");
            if (null != arrays) {
                for (int i = 0; i < arrays.size(); i++) {
                    CarModel entity = new CarModel();
                    JSONObject item = arrays.getJSONObject(i);
                    entity.setMakeYear(item.optString("makeYear"));
                    entity.setModelId(item.optString("modelId"));
                    entity.setModelName(item.optString("modelName"));
//                    Series series = new Series();
                    entity.setSeriesName(item.getJSONObject("series").getString("name"));
//                    series.setSeriesIds(item.getJSONObject("series").getString("id"));
//                    entity.setSeries(series);
                    list.add(entity);
                }
            }
        }
        return list;
    }

    private CarModel toModelObject(String json) {
        CarModel entity = new CarModel();
        JSONObject obje = JSONObject.fromObject(json);
        if (1 == obje.optInt("status")) {
            JSONObject obj = obje.optJSONObject("data");
            if (null != obj) {
                entity.setBrand(obj.optString("brand"));
                entity.setBrandId(obj.optString("brandId"));
                entity.setMake(obj.optString("make"));
                entity.setMakeId(obj.optString("makeId"));
                entity.setMakeYear(obj.optString("makeYear"));
                entity.setModelId(obj.optString("modelId"));
                entity.setModelName(obj.optString("modelName"));
                entity.setNewPriceW(obj.optString("newPriceW"));
                entity.setSeriesGroupName(obj.optString("seriesGroupName"));
                entity.setSeriesId(obj.optString("seriesId"));
                entity.setSeriesName(obj.optString("seriesName"));
            }
        }
        return entity;
    }



}
