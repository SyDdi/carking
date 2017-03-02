package com.car.mp.service;


import com.car.mp.domain.dto.ModelDTO;
import com.car.mp.util.HttpHandler;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/10/28.
 */
@Service
public class SegmentService {
    private final static String URL_MODEL = "http://120.26.120.93:8923/parse";
    private static Logger logger = Logger.getLogger(SegmentService.class);
    /**
     * 从第三方API获取厂商及车系列表
     * key格式为品牌ID_厂商ID
     */
    LoadingCache<String ,ModelDTO> modelDTOCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*1, TimeUnit.SECONDS).build(new CacheLoader<String, ModelDTO>() {
                public ModelDTO load(String key) throws Exception {
                    String tip = "获取分词 factoryList | 参数：q：" + key;
                    // 请求接口
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("model", key);
                    logger.info(tip + "准备请求第三方接口，URL:" + URL_MODEL + "，参数：" + map);
                    String html = HttpHandler.Get(URL_MODEL, map, 200);
                    logger.info(tip + "准备请求第三方接口返回结果：" + html);
                    ModelDTO model = toObject(html);
                    return model;
                }
            });

    public ModelDTO getModelDTO(String q) {
        ModelDTO modelDTO = null;
        try {
            modelDTO = modelDTOCache.get(q);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return modelDTO;
    }
    /**
     * json 转 对象
     *
     * @param json
     * @return
     */
    private ModelDTO toObject(String json) {
        ModelDTO modelDTO = new ModelDTO();
        JSONObject obj = JSONObject.fromObject(json);
        modelDTO.setBrand(obj.optString("brand"));
        modelDTO.setFactory(obj.optString("facory"));
        modelDTO.setFamily(obj.optString("carline"));
        return modelDTO;

    }

}
