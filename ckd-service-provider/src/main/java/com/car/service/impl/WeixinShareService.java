package com.car.service.impl;

import com.car.domain.Choice;
import com.car.domain.ChoiceExample;
import com.car.domain.WeixinShare;
import com.car.domain.WeixinShareExample;
import com.car.repository.biz.ChoiceMapper;
import com.car.repository.biz.WeixinShareMapper;
import com.car.service.IChoiceService;
import com.car.service.IWeixinShareService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IWeixinShareService")
public class WeixinShareService extends BaseService<WeixinShare> implements IWeixinShareService {

    @Autowired
    private WeixinShareMapper weixinShareMapper;

    @Override
    public int countByExample(WeixinShareExample example) {
        return weixinShareMapper.countByExample2(example);
    }

    @Override
    public int deleteByExample(WeixinShareExample example) {
        return weixinShareMapper.deleteByExample2(example);
    }

    @Override
    public List<WeixinShare> selectByExample(WeixinShareExample example) {
        return weixinShareMapper.selectByExample2(example);
    }

    @Override
    public int updateByExampleSelective(WeixinShare record, WeixinShareExample example) {
        return weixinShareMapper.updateByExampleSelective2(record, example);
    }

    @Override
    public int updateByExample(WeixinShare record, WeixinShareExample example) {
        return weixinShareMapper.updateByExample2(record, example);
    }
}
