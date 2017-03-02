package com.car.service;

import com.car.domain.Collection;

public interface ICollectionService extends IService<Collection> {

    int findCollCount(Collection collection);

}
