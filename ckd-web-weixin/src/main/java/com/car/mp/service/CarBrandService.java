package com.car.mp.service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import com.car.mp.domain.*;
import com.car.mp.util.HttpHandler;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.car.mp.persistence.readonly.CarBrandMapper;

@Service
public class CarBrandService {

	@Resource
    private CarBrandMapper carBrandMapper;
    private static Logger logger = Logger.getLogger("CarBrandService");

    private final static String URL_SERIES_GROUP = "http://api.hangqingjia.com/service/getSeriesGroup";
    private final static String URL_MODEL = "http://api.hangqingjia.com/service/getModel";
    private final static String URL_MODEL_BYID = "http://api.hangqingjia.com/service/getModelById";


//    将一些访问频繁改动很小的数据缓存起来
    public List<CarBrand> getHotCarList() {
    	List<CarBrand> list = carBrandMapper.getHotCarList();
	    logger.debug("getHotCarList success");
	    return list;
    }

    LoadingCache<String, List<CarBrand>> brandCache = CacheBuilder.newBuilder().maximumSize(1)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<String, List<CarBrand>>() {
                public List<CarBrand> load(String key) throws Exception {
                    return carBrandMapper.getAllCarList();
                }
            });
    /**
     * 将品牌缓存起来
     * @return
     */
    public List<CarBrand> getBrandList(){
        List<CarBrand> list = null;
        try{ list = brandCache.get("allBrand"); }catch (Exception e) { }
        return list;
    }
    public LinkedHashMap<String, List<CarBrand>> getAllCarList() {
    	List<CarBrand> list = getBrandList();
		LinkedHashMap<String, List<CarBrand>> result = new LinkedHashMap<String, List<CarBrand>>(); 
		for(CarBrand car : list) {
			List<CarBrand> current = null;
			if( result.containsKey(car.getFirst_letter()) ) {
				current = result.get(car.getFirst_letter());
			} else {
				current = new ArrayList<CarBrand>();
			}
			current.add(car);
			result.put(car.getFirst_letter(), current);
		}
		logger.debug("getAllCarList success");
		return result;
    }
    
	/**
	 * 获取厂商列表
	 * 
	 * @param brandId
	 * @return
	 */
    LoadingCache<Integer, List<CarFactoryEntity>> factoryCache = CacheBuilder.newBuilder().maximumSize(200)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<Integer, List<CarFactoryEntity>>() {
                public List<CarFactoryEntity> load(Integer key) throws Exception {
                    return carBrandMapper.getFactoryList(key);
                }
            });
	public List<CarFactoryEntity> getFactoryList(int brandId) {
        List<CarFactoryEntity> list = null;
        try{ list = factoryCache.get(brandId); }catch (Exception e) { }
        return list;
	}

	/**
	 * 获取品牌
	 *
	 * @param brandId
	 * @return
	 */
    LoadingCache<Integer, CarBrand> brandEntityCache = CacheBuilder.newBuilder().maximumSize(200)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<Integer, CarBrand>() {
                public CarBrand load(Integer key) throws Exception {
                    return carBrandMapper.getBrand(key);
                }
            });
	public CarBrand getBrand(int brandId) {
        CarBrand brand = null;
        try{ brand = brandEntityCache.get(brandId); }catch (Exception e) { }
        return brand;
	}

	/**
	 * 获取厂商
	 *
	 * @param factoryId
	 * @return
	 */
    LoadingCache<Integer, CarFactoryEntity> factoryEntityCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<Integer, CarFactoryEntity>() {
                public CarFactoryEntity load(Integer key) throws Exception {
                    return carBrandMapper.getFactoryById(key);
                }
            });
	public CarFactoryEntity getFactoryById(int factoryId) {
        CarFactoryEntity factoryEntity = null;
        try{ factoryEntity = factoryEntityCache.get(factoryId); }catch (Exception e) { }
        return factoryEntity;
	}

    /**
     * 从第三方API获取厂商及车系列表
     * key格式为品牌ID_厂商ID
     */
    LoadingCache<String ,SeriesGroupEntity> seriesEntityCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<String, SeriesGroupEntity>() {
                public SeriesGroupEntity load(String key) throws Exception {
                    String[] keys =  StringUtils.split(key,"_");
                    String tip = "获取厂商列表 | factoryList | 参数：brandId：" + keys[0] + ", factoryId：" + keys[1];
                    // 请求接口
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("brandId", keys[0]);
                    map.put("makeId", keys[1]);
                    logger.debug(tip + "准备请求第三方接口，URL:" + URL_SERIES_GROUP + "，参数：" + map);
                    String html = HttpHandler.Get(URL_SERIES_GROUP, map, 200);
                    logger.debug(tip + "准备请求第三方接口返回结果：" + html);
                    SeriesGroupEntity seriesGroupEntity = toObject(html);
                    return seriesGroupEntity;
                }
            });
    public SeriesGroupEntity getSeriesGroup(int brandId,int factoryId){
        SeriesGroupEntity seriesGroupEntity = null;
        try{ seriesGroupEntity = seriesEntityCache.get(brandId+"_"+factoryId); }catch (Exception e) { }
        return seriesGroupEntity;
    }

    /**
     * 从第三方API获取年款和车型列表
     * key格式为品牌ID_厂商ID
     */
    LoadingCache<String ,ModelListEntity> modelListEntityCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<String, ModelListEntity>() {
                public ModelListEntity load(String key) throws Exception {
                    String[] keys =  StringUtils.split(key,"_");
                    String tip = "获取车型列表 | factoryList | 参数：brandId：" + keys[0] + ", factoryId：" + keys[1] + ",seriesId： "+keys[2];
                    // 请求接口
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("brandId", keys[0]);
                    map.put("makeId", keys[1]);
                    map.put("seriesIds", keys[2]);
                    logger.debug(tip + "准备请求第三方接口，URL:" + URL_MODEL + "，参数：" + map);
                    String html = HttpHandler.Get(URL_MODEL, map, 200);
                    logger.debug(tip + "准备请求第三方接口返回结果：" + html);
                    ModelListEntity seriesGroupEntity = toModelListObject(html);
                    return seriesGroupEntity;
                }
            });

    public ModelListEntity getModelListEntity(int brandId,int factoryId,String seriesId){
        ModelListEntity modelListEntity = null;
        try{ modelListEntity = modelListEntityCache.get(brandId+"_"+factoryId+"_"+seriesId); }catch (Exception e) { }
        return modelListEntity;
    }

    /**
     * 根据车型ID获取车型并缓存
     */
	LoadingCache<String ,ModelDetailEntity> modelDetailEntityCache = CacheBuilder.newBuilder().maximumSize(2000)
	        .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<String, ModelDetailEntity>() {
	            public ModelDetailEntity load(String key) throws Exception {
	                String tip = "获取车型| getModel | 参数：modelId：" +key;
	                // 请求接口
	                Map<String, Object> map = new HashMap<String, Object>();
	                map.put("modelId", key);
	                logger.debug(tip + "准备请求第三方接口，URL:" + URL_MODEL_BYID + "，参数：" + map);
	                String html = HttpHandler.Get(URL_MODEL_BYID, map, 200);
	                logger.debug(tip + "准备请求第三方接口返回结果：" + html);
	                ModelDetailEntity modelDetail = toModelObject(html);
	                return modelDetail;
	            }
	        });
	public ModelDetailEntity getModelById(String modelId){
		ModelDetailEntity modelDetailEntity=null;
		try{
			modelDetailEntity=modelDetailEntityCache.get(modelId);
		}catch(Exception e){
		}
	    return modelDetailEntity;
	}

    /**
     * json 转 对象
     *
     * @param json
     * @return
     */
    private SeriesGroupEntity toObject(String json) {
        SeriesGroupEntity seriesGroupEntity = new SeriesGroupEntity();
        JSONObject obj = JSONObject.fromObject(json);
        seriesGroupEntity.setStatus(obj.optInt("status"));
        seriesGroupEntity.setMessge(obj.optString("message"));
        if (1 == seriesGroupEntity.getStatus()) {
            JSONArray arrays = obj.optJSONArray("data");
            if (null != arrays) {
                List<SeriesEntity> list = new ArrayList<SeriesEntity>();
                for (int i = 0; i < arrays.size(); i++) {
                    SeriesEntity entity = new SeriesEntity();
                    JSONObject item = arrays.getJSONObject(i);
                    entity.setSeriesIds(item.optString("seriesIds"));
                    entity.setSeriesName(item.optString("seriesName"));
                    list.add(entity);
                }
                seriesGroupEntity.setData(list);
            }
        }
        return seriesGroupEntity;
    }

    /**
     * json 转 对象
     *
     * @param json
     * @return
     */
    private ModelListEntity toModelListObject(String json) {
        ModelListEntity modelListEntity = new ModelListEntity();
        JSONObject obj = JSONObject.fromObject(json);
        modelListEntity.setStatus(obj.optInt("status"));
        modelListEntity.setMessge(obj.optString("message"));
        if (1 == modelListEntity.getStatus()) {
            JSONArray arrays = obj.optJSONArray("data");
            if (null != arrays) {
                List<ModelEntity> list = new ArrayList<ModelEntity>();
                for (int i = 0; i < arrays.size(); i++) {
                    ModelEntity entity = new ModelEntity();
                    JSONObject item = arrays.getJSONObject(i);
                    entity.setMakeYear(item.optString("makeYear"));
                    entity.setModelId(item.optString("modelId"));
                    entity.setModelName(item.optString("modelName"));
                    SeriesEntity series = new SeriesEntity();
                    series.setSeriesName(item.getJSONObject("series").getString("name"));
                    series.setSeriesIds(item.getJSONObject("series").getString("id"));
                    entity.setSeries(series);
                    list.add(entity);
                }
                modelListEntity.setData(list);
            }
        }
        return modelListEntity;
    }

    private ModelDetailEntity toModelObject(String json) {
    	ModelDetailEntity entity = new ModelDetailEntity();
        JSONObject obje = JSONObject.fromObject(json);
        entity.setStatus(obje.optInt("status"));
        entity.setMessge(obje.optString("message"));
		if (1 == entity.getStatus()) {
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
