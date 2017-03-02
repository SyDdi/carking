package com.car.service.impl;


import com.car.domain.CarFactory;
import com.car.domain.Factory;
import com.car.repository.base.FactoryMapper;
import com.car.service.ICarFactoryService;
import com.car.service.IFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IFactoryService")
public class FactoryService extends BaseService<Factory> implements IFactoryService {

    @Autowired
    ICarFactoryService carFactoryService;
    @Autowired
    FactoryMapper factoryMapper;

    @Override
    public List<Factory> selectBy(Integer brandId){

        Example example1 = new Example(CarFactory.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("brandId",brandId);
        List<Factory> list1 = factoryMapper.selectByExample(example1);
        return list1 ;
//        Example example = new Example(CarFactory.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("brandId",brandId);
//        List<CarFactory> list = carFactoryService.selectByExample(example);
//        List<Factory> factories = new ArrayList<Factory>();
//        for (CarFactory factory:list){
//            Factory f  = new Factory();
//            f.setFactoryId(factory.getFactoryId());
//            f.setFactoryName(factory.getFactoryNameCn());
//            f.setBrandId(factory.getBrandId());
//            factories.add(f);
//        }
//        return factories;
    }

}
