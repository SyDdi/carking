package com.car.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.WeixinPushMsg;
import com.car.domain.WeixinPushMsgExample;
import com.car.repository.biz.WeixinPushMsgMapper;
import com.car.service.IWeixinPushMsgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IWeixinPushMsgService")
public class WeixinPushMsgServiceImpl extends BaseService<WeixinPushMsg> implements IWeixinPushMsgService {

    @Reference
    private WeixinPushMsgMapper weixinPushMsgMapper;

    @Override
    public int countByExample(WeixinPushMsgExample example) {
        return weixinPushMsgMapper.countByExample2(example);
    }

    @Override
    public int deleteByExample(WeixinPushMsgExample example) {
        return weixinPushMsgMapper.deleteByExample2(example);
    }

    @Override
    public List<WeixinPushMsg> selectByExample(WeixinPushMsgExample example) {
        return weixinPushMsgMapper.selectByExample2(example);
    }

    @Override
    public int updateByExampleSelective(WeixinPushMsg record, WeixinPushMsgExample example) {
        return weixinPushMsgMapper.updateByExampleSelective2(record,example);
    }

    @Override
    public int updateByExample(WeixinPushMsg record, WeixinPushMsgExample example) {
        return weixinPushMsgMapper.updateByExample2(record, example);
    }
}
