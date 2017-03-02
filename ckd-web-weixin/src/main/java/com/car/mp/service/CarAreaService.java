package com.car.mp.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import com.car.mp.domain.CarAreaEntity;
import com.car.mp.persistence.readonly.CarAreaMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 地区
 * 
 * @author wangjh7
 * @date 2016-10-01
 */
@Service
public class CarAreaService {

	@Resource
	private CarAreaMapper carAreaMapper;

	final static Logger LOG = LoggerFactory.getLogger(CarAreaService.class);
	/**
	 * 获取列表
	 * 
	 * @return	 */
	public List<CarAreaEntity> list() {
		LOG.info("list | 获取地区列表 |");
		List<CarAreaEntity> list = carAreaMapper.list();
		return list;
	}

    public CarAreaEntity get(int areaId){
        return carAreaMapper.get(areaId);
    }

    /**
     public CarBrand getBrand(int brandId) {
     CarBrand brand = null;
     try{ brand = brandEntityCache.get(brandId); }catch (Exception e) { }
     return brand;
     }
     */
}
