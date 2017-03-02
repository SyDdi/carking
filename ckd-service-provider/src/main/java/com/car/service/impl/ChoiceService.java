package com.car.service.impl;

import com.car.domain.Choice;
import com.car.domain.ChoiceExample;
import com.car.repository.biz.ChoiceMapper;
import com.car.service.IChoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IChoiceService")
public class ChoiceService extends BaseService<Choice> implements IChoiceService {

    @Resource
    private ChoiceMapper choiceMapper;

    @Override
    public int countByExample(ChoiceExample example) {
        return choiceMapper.countByExample2(example);
    }

    @Override
    public int deleteByExample(ChoiceExample example) {
        return choiceMapper.deleteByExample2(example);
    }

    @Override
    public List<Choice> selectByExample(ChoiceExample example) {
        return choiceMapper.selectByExample2(example);
    }

    @Override
    public int updateByExampleSelective(Choice record, ChoiceExample example) {
        return choiceMapper.updateByExampleSelective2(record, example);
    }

    @Override
    public int updateByExample(Choice record, ChoiceExample example) {
        return choiceMapper.updateByExample2(record, example);
    }
    
    @Override
	public List<Choice> findByIdsMap(List<Long> choiceIds){
    	return choiceMapper.findByIdsMap(choiceIds);
    }
}
