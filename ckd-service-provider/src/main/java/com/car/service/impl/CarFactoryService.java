package com.car.service.impl;


import com.car.domain.Brand;
import com.car.domain.CarFactory;
import com.car.service.IBrandService;
import com.car.service.ICarFactoryService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/6.
 */

@Service("ICarFactoryService")
public class CarFactoryService extends BaseService<CarFactory> implements ICarFactoryService {
    
}
