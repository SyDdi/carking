package com.car.service;

import com.car.domain.Report;
import com.car.domain.ReportExample;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface IReportService extends IService<Report> {
    int countByExample(ReportExample example);

    int deleteByExample(ReportExample example);

    List<Report> selectByExample(ReportExample example);

    int updateByExampleSelective( Report record, ReportExample example);

    int updateByExample(Report record, ReportExample example);
}
