package com.car.repository.biz;

import com.car.domain.WeixinPushMsg;
import com.car.domain.WeixinPushMsgExample;
import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeixinPushMsgMapper extends IMapper<WeixinPushMsg> {
    int countByExample2(WeixinPushMsgExample example);

    int deleteByExample2(WeixinPushMsgExample example);

    List<WeixinPushMsg> selectByExample2(WeixinPushMsgExample example);

    int updateByExampleSelective2(@Param("record") WeixinPushMsg record, @Param("example") WeixinPushMsgExample example);

    int updateByExample2(@Param("record") WeixinPushMsg record, @Param("example") WeixinPushMsgExample example);
}