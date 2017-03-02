package com.car.repository.biz;

import com.car.domain.Vehicle;
import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VehicleMapper extends IMapper<Vehicle>{

    List<Vehicle> selectVehicle( Map map);

    int findCount();

    List<Vehicle> selectVehiclPage( Map map);

    Vehicle findVehicleById(Long id);

    List<Vehicle> selectVhclByUserId(Long userId, Integer status);
}