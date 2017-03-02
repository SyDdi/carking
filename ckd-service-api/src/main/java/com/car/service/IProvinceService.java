package com.car.service;

import com.alibaba.fastjson.JSONObject;
import com.car.domain.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface IProvinceService extends IService<Province>{

//    public List<Province> selectAll();

    /**
     * 获取品牌Json数据
     * @return
     */
    @Deprecated
    Object provinceList();

}
