package com.car.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.car.domain.Province;
import com.car.domain.Zone;
import com.car.service.IProvinceService;
import com.car.service.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IZoneService")
public class ZoneService extends BaseService<Zone> implements IZoneService {

//    @Override
//    public List<Zone> selectByProvince(Integer provinceId) {
//        return null;
//    }

    public Object zoneList(Integer provinceId){
        Example example = new Example(Zone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("provinceId",provinceId);
        //得到所有厂商
        List<Zone> list = this.selectByExample(example);
        List<JSONObject> result = new ArrayList<JSONObject>();
        for(Zone zone:list){
            JSONObject o = new JSONObject();
            o.put("id",zone.getId());
            o.put("name",zone.getZoneName());
            result.add(o);
        }
        return result;
    }

}
