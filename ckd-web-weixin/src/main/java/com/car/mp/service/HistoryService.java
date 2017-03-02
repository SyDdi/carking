package com.car.mp.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import com.car.mp.domain.HistoryEntity;
import com.car.mp.persistence.readonly.HistoryMapper;
import org.apache.log4j.Logger;

/**
 * Created by x201 on 2016/9/20.
 */
@Service
public class HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    private static Logger logger = Logger.getLogger("OperationLogService");

    /**
     * 获取历史估价记录
     * @return
     */
    public List<HistoryEntity> getOperationRecorders(String openId){
        List<HistoryEntity> list = historyMapper.getHistoryRecorders(openId);
        logger.info("getHistoryRecorders success");
        return list;
    }

    /**
     * 保存历史估价记录
     * @return
     */
    public Long saveOperationRecorder(HistoryEntity recorder){
        Long result = historyMapper.saveHistoryRecorder(recorder);
        logger.info("saveHistoryRecorder");
        return result;
    }
}
