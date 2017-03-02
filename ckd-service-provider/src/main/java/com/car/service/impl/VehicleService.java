package com.car.service.impl;

import javax.annotation.Resource;

import com.car.Page;
import com.car.repository.biz.VehicleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;
import com.car.domain.User;
import com.car.domain.Vehicle;
import com.car.repository.biz.UserMapper;
import com.car.service.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
@Component
@Service("IVehicleService")
public class VehicleService extends BaseService<Vehicle> implements IVehicleService {
	@Resource
	UserMapper userMapper;

	@Resource
	private VehicleMapper vehicleMapper;

    public Vehicle selectFullByKey(Object key) {
    	Vehicle vehicle = mapper.selectByPrimaryKey(key);
    	System.out.println("vehicle.getUserId():"+vehicle.getUserId());
    	User user = userMapper.selectByPrimaryKey(vehicle.getUserId());
    	vehicle.setUser(user);
    	return vehicle;
    }
/*    public List<Vehicle> selectFullByExample(Object example){
    	mapper.selectByExample(example);
    }*/
	@Override
	public List<Vehicle> selectVehicle(Map map){
		List<Vehicle> list = vehicleMapper.selectVehicle(map);
		return list;
	}

	@Override
	public int findCount() {
		return vehicleMapper.findCount();
	}

	@Override
	public Page<Vehicle> findVehicle(Map map, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<Vehicle> list = vehicleMapper.selectVehiclPage(map);
		return new Page<>(list);
	}

	@Override
	public Vehicle findVehicleById(Long id) {
		return vehicleMapper.findVehicleById(id);
	}

	@Override
	public List<Vehicle> selectVhclByUserId(Long userId, Integer status) {
		return vehicleMapper.selectVhclByUserId(userId,status);
	}


}
