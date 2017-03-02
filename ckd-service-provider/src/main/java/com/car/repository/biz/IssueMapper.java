package com.car.repository.biz;

import com.car.domain.Issue;
import com.car.domain.IssueExample;
import java.util.List;

import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface IssueMapper extends IMapper<Issue> {
    int countByExample2(IssueExample example);

    int deleteByExample2(IssueExample example);

    List<Issue> selectByExample2(IssueExample example);

    int updateByExampleSelective2(@Param("record") Issue record, @Param("example") IssueExample example);

    int updateByExample2(@Param("record") Issue record, @Param("example") IssueExample example);
    
    List<Issue> findByIdsMap(List<Long> issueIds);
}