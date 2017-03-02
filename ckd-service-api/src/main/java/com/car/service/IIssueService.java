package com.car.service;


import com.car.domain.Issue;
import com.car.domain.IssueExample;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface IIssueService extends IService<Issue> {
    int countByExample(IssueExample example);

    int deleteByExample(IssueExample example);

    List<Issue> selectByExample(IssueExample example);

    int updateByExampleSelective( Issue record,IssueExample example);

    int updateByExample(Issue record, IssueExample example);
    
    List<Issue> findByIdsMap(List<Long> issueIds);
}
