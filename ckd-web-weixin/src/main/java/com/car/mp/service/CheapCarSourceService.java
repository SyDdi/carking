package com.car.mp.service;

import javax.annotation.Resource;

import com.car.mp.domain.CarAreaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.car.mp.domain.CheapCarSourceEntity;
import com.car.mp.domain.dto.CheapCarSourceDTO;
import com.car.mp.persistence.readonly.CheapCarSourceMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 新车价格预警
 *
 * @author wangjh7
 * @date 2016-09-25
 */
@Service
public class CheapCarSourceService {

    @Resource
    private CheapCarSourceMapper cheapCarSourceMapper;

    final static Logger LOG = LoggerFactory.getLogger(CheapCarSourceService.class);

    /**
     * 获取列表
     *
     * @return
     */
    public List<CheapCarSourceEntity> list(CheapCarSourceDTO dto) {
        LOG.info("list | 获取列表 | 参数：dto={}", dto);
        List<CheapCarSourceEntity> list = cheapCarSourceMapper.list(dto);
        return list;
    }

    /**
     * 获取列表
     *
     * @return
     */
    public int listCount(CheapCarSourceDTO dto) {
        LOG.info("listCount | 获取列表总数 | 参数：dto={}", dto);
        return cheapCarSourceMapper.listCount(dto);
    }

    /**
     * 获取当前查询条件下的品牌列表
     *
     * @param dto
     * @return
     */
    public List<CheapCarSourceEntity> brandList(CheapCarSourceDTO dto) {
        LOG.info("brandList | 获取品牌列表 | 参数：dto={}", dto);
        return cheapCarSourceMapper.brandList(dto);
    }

    /**
     * 获取当前查询条件下的品牌列表
     *
     * @param dto
     * @return
     */
    public List<CarAreaEntity> areaList(CheapCarSourceDTO dto) {
        LOG.info("brandList | 获取城市列表 | 参数：dto={}", dto);
        return cheapCarSourceMapper.areaList(dto);
    }
}
