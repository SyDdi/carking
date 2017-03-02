package com.car.repository.biz;

import com.car.domain.Report;
import com.car.domain.ReportExample;
import java.util.List;

import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ReportMapper extends IMapper<Report> {
    int countByExample2(ReportExample example);

    int deleteByExample2(ReportExample example);

    List<Report> selectByExample2(ReportExample example);

    int updateByExampleSelective2(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExample2(@Param("record") Report record, @Param("example") ReportExample example);
}