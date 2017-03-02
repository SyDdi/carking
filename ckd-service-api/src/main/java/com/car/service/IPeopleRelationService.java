package com.car.service;

import com.car.domain.PeopleRelation;
import com.car.domain.PeopleRelationExample;

import java.util.List;

/**
 * Created by admin on 2016/12/26.
 */
public interface IPeopleRelationService{

    int insert(PeopleRelation record);

    int countByExample(PeopleRelationExample example);

    int deleteByExample(PeopleRelationExample example);

    List<PeopleRelation> selectByExample(PeopleRelationExample example);

    int updateByExampleSelective(PeopleRelation record, PeopleRelationExample example);

    int updateByExample( PeopleRelation record, PeopleRelationExample example);

    void saveFriend( String source,Long userId);

}
