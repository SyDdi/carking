package com.car.service.impl;

import com.car.domain.Issue;
import com.car.domain.IssueExample;
import com.car.repository.biz.IssueMapper;
import com.car.service.IIssueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IIssueService")
public class IssueService extends BaseService<Issue> implements IIssueService {

    @Resource
    private IssueMapper IssueMapper;

    @Override
    public int countByExample(IssueExample example) {
        return IssueMapper.countByExample2(example);
    }

    @Override
    public int deleteByExample(IssueExample example) {
        return IssueMapper.deleteByExample2(example);
    }

    @Override
    public List<Issue> selectByExample(IssueExample example) {
        return IssueMapper.selectByExample2(example);
    }

    @Override
    public int updateByExampleSelective(Issue record, IssueExample example) {
        return IssueMapper.updateByExampleSelective2(record, example);
    }

    @Override
    public int updateByExample(Issue record, IssueExample example) {
        return IssueMapper.updateByExample2(record, example);
    }
    
    @Override
    public List<Issue> findByIdsMap(List<Long> issueIds){
    	return IssueMapper.findByIdsMap(issueIds);
    }
}
