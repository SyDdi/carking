package com.car.mp.persistence.readonly;

import com.car.mp.domain.PriceAlarmEntity;
import com.car.mp.domain.dto.PriceAlarmDTO;

import java.util.List;

/**
 * 新车价格预警
 * 
 * @author wangjh7
 * @date 2016-09-25
 */
public interface PriceAlarmMapper {

	/**
	 * 获取列表
	 * 
	 * @param date
	 * @return
	 */
	public List<PriceAlarmEntity> list(PriceAlarmDTO dto);
	
	/**
	 * 获取列表
	 * 
	 * @param date
	 * @return
	 */
	public int listCount(PriceAlarmDTO dto);
}
