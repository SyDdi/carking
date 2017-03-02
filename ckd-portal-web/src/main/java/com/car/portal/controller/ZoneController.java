package com.car.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.car.domain.Province;
import com.car.domain.Zone;
import com.car.exception.ParameterException;
import com.car.portal.utils.RestResultGenerator;
import com.car.service.IProvinceService;
import com.car.service.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
@RestController
@RequestMapping(value = "/api/zone")
public class ZoneController {

    @Reference
    IZoneService zoneService;
    @Reference
    IProvinceService provinceService;

    /*
    获取省份Json数据
     */
    @RequestMapping(value = "/province")
    public Object provinceList() {
//        List<Province> list = provinceService.selectAll();
        Province province = new Province();
        province.setStatus(1);
        List<Province> list = provinceService.select(province);

        List<JSONObject> result = new ArrayList<JSONObject>();
        for(Province p:list){
            JSONObject o = new JSONObject();
            o.put("id",p.getId());
            o.put("name",p.getProvinceName());
            result.add(o);
        }
        return RestResultGenerator.genResult(result, "成功!");
        //return result;
    }

    @RequestMapping(value = "/list")
    public Object zoneList(@RequestParam("provinceId") Long provinceId) {
        Zone z = new Zone();
        z.setProvinceId(provinceId);
        //得到所有
        List<Zone> list = zoneService.select(z);
        List<JSONObject> result = new ArrayList<JSONObject>();
        for(Zone zone:list){
            JSONObject o = new JSONObject();
            o.put("id",zone.getId());
            o.put("name",zone.getZoneName());
            result.add(o);
        }
        return RestResultGenerator.genResult(result, "成功!");
//        return result;
    }
}
