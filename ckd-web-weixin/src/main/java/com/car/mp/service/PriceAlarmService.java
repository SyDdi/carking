package com.car.mp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.car.mp.domain.PriceAlarmEntity;
import com.car.mp.domain.dto.PriceAlarmDTO;
import com.car.mp.persistence.readonly.PriceAlarmMapper;

/**
 * 新车价格预警
 * 
 * @author wangjh7
 * @date 2016-09-25
 */
@Service
public class PriceAlarmService {

	@Resource
	private PriceAlarmMapper priceAlarmMapper;

	final static Logger LOG = LoggerFactory.getLogger(PriceAlarmService.class);

	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<PriceAlarmEntity> list(PriceAlarmDTO dto) {
		LOG.info("list | 获取列表 | 参数：dto={}", dto);
		List<PriceAlarmEntity> list = priceAlarmMapper.list(dto);
		return list;
	}

	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public int listCount(PriceAlarmDTO dto) {
		LOG.info("listCount | 获取列表总数 | 参数：dto={}", dto);
		return priceAlarmMapper.listCount(dto);
	}
}
