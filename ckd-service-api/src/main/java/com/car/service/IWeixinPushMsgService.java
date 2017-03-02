package com.car.service;

import com.car.domain.WeixinPushMsg;
import com.car.domain.WeixinPushMsgExample;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface IWeixinPushMsgService extends IService<WeixinPushMsg> {

    int countByExample(WeixinPushMsgExample example);

    int deleteByExample(WeixinPushMsgExample example);

    List<WeixinPushMsg> selectByExample(WeixinPushMsgExample example);

    int updateByExampleSelective( WeixinPushMsg record,WeixinPushMsgExample example);

    int updateByExample( WeixinPushMsg record,WeixinPushMsgExample example);
}
