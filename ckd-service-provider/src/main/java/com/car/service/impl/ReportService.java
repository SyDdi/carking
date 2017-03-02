package com.car.service.impl;

import com.car.domain.Report;
import com.car.domain.ReportExample;
import com.car.repository.biz.ReportMapper;
import com.car.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IReportService")
public class ReportService extends BaseService<Report> implements IReportService {

    @Resource
    private ReportMapper ReportMapper;

    @Override
    public int countByExample(ReportExample example) {
        return ReportMapper.countByExample2(example);
    }

    @Override
    public int deleteByExample(ReportExample example) {
        return ReportMapper.deleteByExample2(example);
    }

    @Override
    public List<Report> selectByExample(ReportExample example) {
        return ReportMapper.selectByExample2(example);
    }

    @Override
    public int updateByExampleSelective(Report record, ReportExample example) {
        return ReportMapper.updateByExampleSelective2(record, example);
    }

    @Override
    public int updateByExample(Report record, ReportExample example) {
        return ReportMapper.updateByExample2(record, example);
    }
}
