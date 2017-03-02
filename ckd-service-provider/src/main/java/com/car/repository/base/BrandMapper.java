package com.car.repository.base;

import com.car.domain.Brand;
import com.car.domain.CarFactory;
import com.car.repository.IMapper;

public interface BrandMapper extends IMapper<Brand> {
    CarFactory getFactoryById(int key);
}