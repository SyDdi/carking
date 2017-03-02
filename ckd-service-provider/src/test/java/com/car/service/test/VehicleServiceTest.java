package com.car.service.test;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.domain.Vehicle;
import com.car.repository.biz.VehicleMapper;
import com.car.service.IVehicleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class VehicleServiceTest extends BasicTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Autowired
	private SqlSession sqlSessionBiz;
	
	@Autowired
	private IVehicleService iVehicleService;
	
	@Test
	public void select() {
		VehicleMapper vehicleMapper=sqlSessionBiz.getMapper(VehicleMapper.class);
		Example example=new Example(Vehicle.class);
		List<Vehicle> list = vehicleMapper.selectByExample(example);
		//分页查询：第一页，共三页
		PageHelper.startPage(1, 3);
		PageInfo<Vehicle> pageInfo=new PageInfo<Vehicle>(list);
		List<Vehicle> dataList = pageInfo.getList();
		for (Vehicle vehicle : dataList) {
			System.out.println(vehicle);
		}
		System.out.println("count: "+list.size());
	}
	
	@Test
	public void get(){

        System.out.println(iVehicleService.selectFullByKey(1L).getUser().getUserName());
	}

}
