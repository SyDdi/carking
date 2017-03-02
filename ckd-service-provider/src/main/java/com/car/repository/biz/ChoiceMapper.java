package com.car.repository.biz;

import com.car.domain.Choice;
import com.car.domain.ChoiceExample;
import com.car.domain.Issue;

import java.util.List;

import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ChoiceMapper extends IMapper<Choice> {

    int countByExample2(ChoiceExample example);

    int deleteByExample2(ChoiceExample example);

    List<Choice> selectByExample2(ChoiceExample example);

    int updateByExampleSelective2(@Param("record") Choice record, @Param("example") ChoiceExample example);

    int updateByExample2(@Param("record") Choice record, @Param("example") ChoiceExample example);
    
    List<Choice> findByIdsMap(List<Long> choiceIds);
}