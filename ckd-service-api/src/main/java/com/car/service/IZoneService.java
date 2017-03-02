package com.car.service;

import com.car.domain.Zone;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface IZoneService extends IService<Zone>{

//    public List<Zone> selectByProvince(Integer provinceId);

    Object zoneList(Integer provinceId);
}
