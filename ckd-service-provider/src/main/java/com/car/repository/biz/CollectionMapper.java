package com.car.repository.biz;

import com.car.domain.Collection;
import com.car.repository.IMapper;

public interface CollectionMapper extends IMapper<Collection> {

    int findCollCount(Collection collection);

}