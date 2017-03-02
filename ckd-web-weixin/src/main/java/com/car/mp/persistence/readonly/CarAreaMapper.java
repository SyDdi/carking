package com.car.mp.persistence.readonly;

import com.car.mp.domain.CarAreaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 地区
 * 
 * @author wangjh7
 * @date 2016-10-01
 */
public interface CarAreaMapper {

	/**
	 * 获取列表
	 * 
	 * @param date
	 * @return
	 */
	public List<CarAreaEntity> list();

    /**
     * 获取指定品牌
     * @param areaId
     * @return
     */
    public CarAreaEntity get(@Param(value = "areaId") int areaId) ;

}
