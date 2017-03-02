package com.car.repository.biz;

import com.car.domain.WeixinShare;
import com.car.domain.WeixinShareExample;
import java.util.List;

import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface WeixinShareMapper extends IMapper<WeixinShare> {
    int countByExample2(WeixinShareExample example);

    int deleteByExample2(WeixinShareExample example);

    List<WeixinShare> selectByExample2(WeixinShareExample example);

    int updateByExampleSelective2(@Param("record") WeixinShare record, @Param("example") WeixinShareExample example);

    int updateByExample2(@Param("record") WeixinShare record, @Param("example") WeixinShareExample example);
}