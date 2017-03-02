package com.car.service;

import com.car.domain.Factory;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface IFactoryService extends IService<Factory>{

    public List<Factory> selectBy(Integer brandId);
}
