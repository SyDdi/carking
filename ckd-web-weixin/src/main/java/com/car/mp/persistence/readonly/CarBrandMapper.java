package com.car.mp.persistence.readonly;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.car.mp.domain.CarBrand;
import com.car.mp.domain.CarFactoryEntity;

public interface CarBrandMapper {

	/**
	 * 获取热门车型
	 */
	public List<CarBrand> getHotCarList();

	/**
	 * 获取车型列表
	 */
	public List<CarBrand> getAllCarList();

	/**
	 * 获取厂商列表
	 * 
	 * @param brandId
	 * @return
	 */
	public List<CarFactoryEntity> getFactoryList(@Param(value = "brandId") int brandId);


	/**
	 * 获取指定品牌
	 * @param brandId
	 * @return
	 */
	public CarBrand getBrand(@Param(value = "brandId") int brandId) ;

	/**
	 * 获取指定车系
	 * @param factoryId
	 * @return
	 */
	public CarFactoryEntity getFactoryById(@Param(value = "factoryId") int factoryId) ;
}
