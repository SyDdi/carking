package com.car.mp.persistence.readonly;

import com.car.mp.domain.HistoryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by x201 on 2016/9/20.
 */
public interface HistoryMapper {
    /**
     * 获取操作日志
     */
    public List<HistoryEntity> getHistoryRecorders(@Param(value = "openId") String openId);

    public Long saveHistoryRecorder(HistoryEntity recorder);
}
