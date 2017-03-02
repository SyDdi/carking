package com.car.service.impl;


import com.car.core.utils.Helper;
import com.car.domain.CarModel;
import com.car.domain.Model;
import com.car.repository.base.ModelMapper;
import com.car.service.ICarBrandService;
import com.car.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IModelService")
public class ModelService extends BaseService<Model> implements IModelService {

    @Autowired
    ICarBrandService carBrandService;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<Model> selectBy(Integer brandId,Integer factoryId,String subFamilyIds){
        Model model = new Model();
        model.setBrandId(brandId);
        model.setFactoryId(factoryId);
        model.setFamilyId(Integer.parseInt(subFamilyIds));
        List<Model> list = modelMapper.select(model);
        return list;


//        List<CarModel> models = carBrandService.getModelList(brandId,factoryId,subFamilyIds);
//        List<Model> result = new ArrayList<Model>();
//        for(CarModel model1:models) {
//            Model m = convert(model1);
//            result.add(m);
//        }
//        return result;
    }

    @Override
    public Model selectByKey(Integer modelId){
        Model mo = new Model();
        mo.setModelId(modelId);
        List<Model> list = modelMapper.select(mo);
//        CarModel  model = carBrandService.getModelById(modelId);
//        Model m = convert(model);
        return list.size() > 0 ? list.get(0):null;
    }

    private Model convert(CarModel model){
        Model m = new Model();
        m.setBrandId(Helper.parseInt(model.getBrandId()));
        m.setBrand(model.getBrand());
        m.setFactoryId(Helper.parseInt(model.getMakeId()));
        m.setFactory(model.getMake());

        m.setFamilyId(Helper.parseInt(model.getSeriesId()));
        m.setFamilyGroupName("");
        if(model.getSeriesGroupName()!= null && model.getSeriesName() != null && !model.getSeriesGroupName().equals( model.getSeriesName())) {
            m.setFamilyGroupName(model.getSeriesGroupName());
        }
        m.setFamily(model.getSeriesName());

        m.setModelId(Helper.parseInt(model.getModelId()));
        m.setShortName(model.getModelName());
        m.setCarYear(model.getMakeYear());

        m.setPriceInfo(model.getNewPriceW());
        return m;
    }

    
}
