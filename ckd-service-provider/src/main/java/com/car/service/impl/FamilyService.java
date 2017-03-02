package com.car.service.impl;

import com.car.domain.Family;
import com.car.repository.base.FamilyMapper;
import com.car.service.ICarBrandService;
import com.car.service.IFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IFamilyService")
public class FamilyService extends BaseService<Family> implements IFamilyService {

    @Autowired
    ICarBrandService carBrandService;
    @Autowired
    FamilyMapper familyMapper;
    /**
     * 根据品牌和厂商得到车系组
     * @param brandId
     * @param factoryId
     * @return
     */
    @Override
    public List<Family> selectBy(Integer brandId,Integer factoryId){
        Family family1 = new Family();
        family1.setBrandId(brandId);
        family1.setFactoryId(factoryId);
        List<Family> list = familyMapper.select(family1);
        return list;


//        SeriesGroup seriesGroup = carBrandService.getSeriesGroup(brandId, factoryId);
//        List<Series> ss = seriesGroup.getData();
//        List<Family> fs = new ArrayList<Family>();
//        if(ss!=null) {
//            for (Series s : ss) {
//                Family family = new Family();
//                family.setFamilyName(s.getSeriesName());
//                family.setSubFamilyIds(s.getSeriesIds());
//                fs.add(family);
//            }
//        }
//        return fs;
    }
}
