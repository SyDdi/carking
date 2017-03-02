package com.car.service;

import com.car.domain.Brand;
import com.car.domain.CarFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface IBrandService extends IService<Brand>{

    @Deprecated
    public List<Brand> select();

    Object brandList();

    Object familyList(Integer brandId) ;

    Object modelList( Integer brandId,Integer factoryId, String seriesIds);

    Brand selectByBrandId(Long brandId );

    CarFactory getFactoryById(int factoryId);
}
