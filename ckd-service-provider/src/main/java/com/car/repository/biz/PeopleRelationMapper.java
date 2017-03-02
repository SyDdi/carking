package com.car.repository.biz;

import com.car.domain.PeopleRelation;
import com.car.domain.PeopleRelationExample;
import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleRelationMapper extends IMapper<PeopleRelation>{
    int countByExample2(PeopleRelationExample example);

    int deleteByExample2(PeopleRelationExample example);

    List<PeopleRelation> selectByExample2(PeopleRelationExample example);

    int updateByExampleSelective2(@Param("record") PeopleRelation record, @Param("example") PeopleRelationExample example);

    int updateByExample2(@Param("record") PeopleRelation record, @Param("example") PeopleRelationExample example);
}