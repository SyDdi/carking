package com.car.service;

import com.car.domain.Choice;
import com.car.domain.ChoiceExample;
import org.jboss.logging.Param;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface IChoiceService extends IService<Choice> {

    int countByExample(ChoiceExample example);

    int deleteByExample(ChoiceExample example);

    List<Choice> selectByExample(ChoiceExample example);

    int updateByExampleSelective(Choice record, ChoiceExample example);

    int updateByExample( Choice record, ChoiceExample example);
    
    List<Choice> findByIdsMap(List<Long> choiceIds);
}
