package com.car.service;

import com.car.domain.WeixinShare;
import com.car.domain.WeixinShareExample;
import org.jboss.logging.Param;

import java.util.List;

/**
 * Created by admin on 2016/12/7.
 */
public interface IWeixinShareService extends IService<WeixinShare> {
    int countByExample(WeixinShareExample example);

    int deleteByExample(WeixinShareExample example);

    List<WeixinShare> selectByExample(WeixinShareExample example);

    int updateByExampleSelective( WeixinShare record, WeixinShareExample example);

    int updateByExample( WeixinShare record,WeixinShareExample example);

}
