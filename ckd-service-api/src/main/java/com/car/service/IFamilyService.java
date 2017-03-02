package com.car.service;

import com.car.domain.Family;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface IFamilyService extends IService<Family>{

    public List<Family> selectBy(Integer brandId,Integer factoryId);
}
