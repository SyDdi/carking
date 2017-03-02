package com.car.service;

import com.car.Page;
import com.car.domain.Vehicle;

import java.util.List;
import java.util.Map;

public interface IVehicleService extends IService<Vehicle> {
	public Vehicle selectFullByKey(Object key);

	List<Vehicle> selectVehicle(Map map);

	int findCount();

	Page<Vehicle> findVehicle(Map map, Integer pageNo, Integer pageSize);

	Vehicle findVehicleById(Long id);

	List<Vehicle> selectVhclByUserId(Long userId ,Integer status);
}
