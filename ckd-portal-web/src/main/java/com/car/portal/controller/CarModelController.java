package com.car.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.portal.utils.RestResultGenerator;
import com.car.service.IBrandService;
import com.car.service.IFactoryService;
import com.car.service.IFamilyService;
import com.car.service.IModelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/11/10.
 */
@RestController
@RequestMapping(value = "/api/model")
public class CarModelController{


    @Reference
    IBrandService brandService;
    @Reference
    IFactoryService factoryService;
    @Reference
    IFamilyService familyService;
    @Reference
    IModelService modelService;

    /*
    获取品牌Json数据
     */
    @RequestMapping(value = "/brand")
    public Object brandList() {
       Object result = brandService.brandList();
//        List<Brand> list = brandService.selectAll();
//        TreeSet letters = new TreeSet();
//        LinkedHashMap<String,ArrayList<JSONObject>> brandsMap = new LinkedHashMap<String,ArrayList<JSONObject>>();
//        for(Brand brand : list) {
//            String letter = brand.getLetter();
//            letters.add(letter);
//            ArrayList<JSONObject> items;
//            if( brandsMap.containsKey(letter) ) {
//                items = brandsMap.get(letter);
//            } else {
//                items = new ArrayList<JSONObject>();
//            }
//            JSONObject item = new JSONObject();
//            item.put("id",brand.getBrandId());
//            item.put("name",brand.getBrandName());
//            item.put("pic",brand.getPicture());
//            items.add(item);
//            brandsMap.put(letter, items);
//        }
//        JSONObject result = new JSONObject();
//        result.put("letters", letters);
//        result.put("brands",brandsMap);
        return RestResultGenerator.genResult(result, "成功!");
    }

    @RequestMapping(value = "/family")
    public Object familyList(@RequestParam("brandId") Integer brandId) {
        Object result = brandService.familyList(brandId);
//        //得到所有厂商
//        List<Factory> list = factoryService.selectBy(brandId);
//        ArrayList<JSONObject> factoryList = new ArrayList<JSONObject>();
////        TreeMap<Integer,List<Series>> familyMap = new TreeMap<Integer,List<Series>>();
//        ArrayList<JSONObject> familyList = new ArrayList<JSONObject>();
//        for(Factory factory : list) {
//            JSONObject fa = new JSONObject();
//            int factoryId = factory.getFactoryId();
//            String factoryName = factory.getFactoryName();
//            fa.put("id",factoryId);
//            fa.put("name",factoryName);
//            factoryList.add(fa);
//            //取得该厂商下的车系组
//            List<Family> fas = familyService.selectBy(brandId,factoryId);
//
//            List<JSONObject> fasJson = new ArrayList<JSONObject>();
//            for(Family f : fas){
//                JSONObject o =  new JSONObject();
//                o.put("seriesName",f.getFamilyName());
////                o.put("seriesIds",f.getSubFamilyIds());
//                o.put("seriesIds",f.getFamilyId());
//                fasJson.add(o);
//            }
//
//            JSONObject family = new JSONObject();
//            family.put(factoryId+"",fasJson);
//            familyList.add(family);
//        }
//        JSONObject result = new JSONObject();
//        result.put("factories", factoryList);
//        result.put("families",familyList);
        return RestResultGenerator.genResult(result, "成功!");
    }

    @RequestMapping(value = "/list")
    public Object modelList(@RequestParam("brandId") Integer brandId,@RequestParam("factoryId") Integer factoryId,@RequestParam("seriesIds") String seriesIds) {
         Object result = brandService.modelList(brandId,factoryId,seriesIds);
//        List<Model> list = modelService.selectBy(brandId, factoryId, seriesIds);
//        TreeSet<String> years = new TreeSet(new Comparator<String>() { public int compare(String arg0, String arg1) {return arg1.compareTo(arg0);}});
//        TreeMap<String,ArrayList<JSONObject>> modelsMap = new TreeMap<String,ArrayList<JSONObject>>();
//        for(Model model : list) {
//            String year = model.getCarYear();
//            years.add(year);
//            ArrayList<JSONObject> items;
//            if( modelsMap.containsKey(year) ) {
//                items = modelsMap.get(year);
//            } else {
//                items = new ArrayList<JSONObject>();
//            }
//            JSONObject item = new JSONObject();
//            item.put("id",model.getModelId());
//            item.put("name",model.getShortName());
//            item.put("price",model.getPriceInfo());
//            item.put("family",model.getFamily());
//            items.add(item);
//            modelsMap.put(year, items);
//        }
//        ArrayList<JSONObject> modelList = new ArrayList<JSONObject>();
//        for(String year:years){
//            JSONObject o = new JSONObject();
//            o.put(year,modelsMap.get(year));
//            modelList.add(o);
//        }
//        JSONObject result = new JSONObject();
//        result.put("years", years);
//        result.put("models",modelList);
        return RestResultGenerator.genResult(result, "成功!");
    }
}
