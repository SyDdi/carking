package com.car.service;


import com.car.domain.Model;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface IModelService extends IService<Model>{

    public List<Model> selectBy(Integer brandId,Integer factoryId,String subFamilyIds);

    public Model selectByKey(Integer modelId);
}
