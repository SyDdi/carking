package com.car.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.car.domain.*;
import com.car.repository.base.BrandMapper;
import com.car.service.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IBrandService")
public class BrandService extends BaseService<Brand> implements IBrandService {

    @Autowired
    ICarBrandService carBrandService;
    @Autowired
    ICarFactoryService carFactoryService;

    @Autowired
    BrandMapper brandMapper;
    @Autowired
    IFactoryService factoryService;
    @Autowired
    IFamilyService familyService;
    @Autowired
    IModelService modelService;


    @Override
    public List<Brand> selectAll(){
     return select();
    }

    @Override
    public List<Brand> select(){

        Example example1 = new Example(Brand.class);
        example1.setOrderByClause(" letter asc");
        List<Brand> brandList = brandMapper.selectByExample(example1);
        return brandList;
//        Example example = new Example(Brand.class);
//        example.setOrderByClause(" first_letter asc");
//        List<CarBrand> list = carBrandService.selectByExample(example);
//        List<Brand> brands = new ArrayList<Brand>();
//        for (CarBrand brand:list){
//            Brand b = new Brand();
//            b.setBrandId(brand.getBrandId());
//            b.setBrandName(brand.getBrandNameCn());
//            b.setLetter(brand.getFirstLetter());
//            b.setPicture(brand.getPicId()+"");
//            brands.add(b);
//        }
//        return brands;
    }

//    @Override
//    public Object brandList() {
//        Example example = new Example(Brand.class);
//        example.setOrderByClause(" first_letter asc");
//        List<CarBrand> list = carBrandService.selectByExample(example);
//        TreeSet letters = new TreeSet();
//        LinkedHashMap<String,ArrayList<JSONObject>> brandsMap = new LinkedHashMap<String,ArrayList<JSONObject>>();
//        for(CarBrand brand : list) {
//            String letter = brand.getFirstLetter();
//            letters.add(letter);
//            ArrayList<JSONObject> items;
//            if( brandsMap.containsKey(letter) ) {
//                items = brandsMap.get(letter);
//            } else {
//                items = new ArrayList<JSONObject>();
//            }
//            JSONObject item = new JSONObject();
//            item.put("id",brand.getBrandId());
//            item.put("name",brand.getBrandNameCn());
//            item.put("pic",brand.getPicId());
//            items.add(item);
//            brandsMap.put(letter, items);
//        }
//        JSONObject result = new JSONObject();
//        result.put("letters", letters);
//        result.put("brands",brandsMap);
//        return result;
//    }

    @Override
    public Object brandList() {
//        Example example = new Example(Brand.class);
//        example.setOrderByClause(" first_letter asc");
//        List<CarBrand> list = carBrandService.selectByExample(example);
//        update to 2017-02-24  从原来的 car_brand 表 改到 新表 t_car_brand 表

        Example example1 = new Example(Brand.class);
        example1.setOrderByClause(" letter asc");
        List<Brand> list = brandMapper.selectByExample(example1);
        TreeSet letters = new TreeSet();
        LinkedHashMap<String,ArrayList<JSONObject>> brandsMap = new LinkedHashMap<String,ArrayList<JSONObject>>();
        for(Brand brand : list) {
            String letter = brand.getLetter();
            letters.add(letter);
            ArrayList<JSONObject> items;
            if( brandsMap.containsKey(letter) ) {
                items = brandsMap.get(letter);
            } else {
                items = new ArrayList<JSONObject>();
            }
            JSONObject item = new JSONObject();
            item.put("id",brand.getBrandId());
            item.put("name",brand.getBrandName());
            item.put("pic",brand.getPicture());
            items.add(item);
            brandsMap.put(letter, items);
        }
        JSONObject result = new JSONObject();
        result.put("letters", letters);
        result.put("brands",brandsMap);
        return result;
    }

    @Override
    public Object familyList(Integer brandId) {
        Example example = new Example(CarFactory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("brandId",brandId);
        //得到所有厂商
//        List<CarFactory> list = carFactoryService.selectByExample(example);
        List<Factory> list = factoryService.selectBy(brandId);
        ArrayList<JSONObject> factoryList = new ArrayList<JSONObject>();
//        TreeMap<Integer,List<Series>> familyMap = new TreeMap<Integer,List<Series>>();
        ArrayList<JSONObject> familyList = new ArrayList<JSONObject>();
        for(Factory factory : list) {
            JSONObject fa = new JSONObject();
            int factoryId = factory.getFactoryId();
            String factoryName = factory.getFactoryName();
            fa.put("id",factoryId);
            fa.put("name",factoryName);
            factoryList.add(fa);
            //取得该厂商下的车系组
            List<Family> fas = familyService.selectBy(brandId,factoryId);
            List<JSONObject> fasJson = new ArrayList<JSONObject>();
            for(Family f : fas){
                JSONObject o =  new JSONObject();
                o.put("seriesName",f.getFamilyName());
//                o.put("seriesIds",f.getSubFamilyIds());
                o.put("seriesIds",f.getFamilyId());
                fasJson.add(o);
            }
            JSONObject family = new JSONObject();
            family.put(factoryId+"",fasJson);
            familyList.add(family);
        }
        JSONObject result = new JSONObject();
        result.put("factories", factoryList);
        result.put("families",familyList);
        return result;
    }

    @Override
    public Object modelList(Integer brandId, Integer factoryId, String seriesIds) {
//        List<CarModel> list = carBrandService.getModelList(brandId,factoryId,seriesIds);
        List<Model> list = modelService.selectBy(brandId, factoryId, seriesIds);
        TreeSet<String> years = new TreeSet(new Comparator<String>() { public int compare(String arg0, String arg1) {return arg1.compareTo(arg0);}});
        TreeMap<String,ArrayList<JSONObject>> modelsMap = new TreeMap<String,ArrayList<JSONObject>>();
        for(Model model : list) {
            String year = model.getCarYear();
            years.add(year);
            ArrayList<JSONObject> items;
            if( modelsMap.containsKey(year) ) {
                items = modelsMap.get(year);
            } else {
                items = new ArrayList<JSONObject>();
            }
            JSONObject item = new JSONObject();
            item.put("id",model.getModelId());
            item.put("name",model.getShortName());
            item.put("price",model.getPriceInfo());
            item.put("family",model.getFamily());
            items.add(item);
            modelsMap.put(year, items);
        }
        ArrayList<JSONObject> modelList = new ArrayList<JSONObject>();
        for(String year:years){
            JSONObject o = new JSONObject();
            o.put(year,modelsMap.get(year));
            modelList.add(o);
        }
        JSONObject result = new JSONObject();
        result.put("years", years);
        result.put("models",modelList);
        return result;
    }

    @Override
    public Brand selectByBrandId(Long brandId) {
        Brand brandParam = new Brand();
        brandParam.setBrandId(Integer.parseInt(brandId+""));
        Brand newBrand = brandMapper.selectOne(brandParam);

//        CarBrand carBrand =  carBrandService.findBrandByBrandId(Integer.parseInt(brandId+""));
//        Brand brand = new Brand();
//        try {
//            brand = this.convert(carBrand);
//        }catch(Exception e){
//        }
        return newBrand;
    }

    /**
     * @param brand
     * @return
     */
    private Brand convert(CarBrand brand){
        Brand b = new Brand();
        b.setBrandId(brand.getBrandId());
        b.setBrandName(brand.getBrandNameCn());
        b.setLetter(brand.getFirstLetter());
        b.setBrandAlias("");
        b.setPicture("");
        b.setRank(brand.getBrandLevel());
        b.setStatus(1);
        return b;
    }

    /**
     * 获取厂商
     *
     * @param factoryId
     * @return
     */
    LoadingCache<Integer, CarFactory> factoryEntityCache = CacheBuilder.newBuilder().maximumSize(2000)
            .expireAfterAccess(60*60*24, TimeUnit.SECONDS).build(new CacheLoader<Integer, CarFactory>() {
                public CarFactory load(Integer key) throws Exception {
                    return brandMapper.getFactoryById(key);
                }
            });
    public CarFactory getFactoryById(int factoryId) {
        CarFactory factoryEntity = null;
        try{ factoryEntity = factoryEntityCache.get(factoryId); }catch (Exception e) { }
        return factoryEntity;
    }

}
