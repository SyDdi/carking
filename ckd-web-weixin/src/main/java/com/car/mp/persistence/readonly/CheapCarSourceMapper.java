package com.car.mp.persistence.readonly;

import com.car.mp.domain.CarAreaEntity;
import com.car.mp.domain.CheapCarSourceEntity;
import com.car.mp.domain.dto.CheapCarSourceDTO;

import java.util.List;

/**
 * 车源
 *
 * @author wangjh7
 * @date 2016-09-28
 */
public interface CheapCarSourceMapper {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    public List<CheapCarSourceEntity> list(CheapCarSourceDTO dto);

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    public int listCount(CheapCarSourceDTO dto);

    /**
     * 获取当前查询条件下的品牌列表
     *
     * @param dto
     * @return
     */
    public List<CheapCarSourceEntity> brandList(CheapCarSourceDTO dto);

    /**
     * 获取当前查询条件下的城市列表
     *
     * @param dto
     * @return
     */
    public List<CarAreaEntity> areaList(CheapCarSourceDTO dto);
}
