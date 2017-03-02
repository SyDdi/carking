package com.car.service;

import com.car.domain.Picture;

import java.util.List;

public interface IPictureService extends IService<Picture> {

    /**
     * 车辆id 和 位置查询图片
     * @param vehicleId
     * @param type 位置 （type = 0 不适用该字段查询）
     * @return
     */
    List<Picture> findImgByVehiclId(Long vehicleId,Integer type);
}
