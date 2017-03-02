package com.car.service;

import com.car.domain.CarBrand;
import com.car.domain.CarModel;
import com.car.domain.SeriesGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
@Deprecated
public interface ICarBrandService extends IService<CarBrand> {

    //
    public SeriesGroup getSeriesGroup(int brandId, int factoryId);

    //根据车系取得车型集合
    public List<CarModel> getModelList(int brandId, int factoryId, String seriesId);

    //根据ID取得车型
    public CarModel getModelById(int modelId);

    CarBrand findBrandByBrandId(Integer brandId);

}
