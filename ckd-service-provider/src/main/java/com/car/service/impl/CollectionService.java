package com.car.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.car.domain.Collection;
import com.car.repository.biz.CollectionMapper;
import com.car.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("ICollectionService")
public class CollectionService extends BaseService<Collection> implements ICollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public int findCollCount(Collection collection) {
        return collectionMapper.findCollCount(collection);
    }
}
