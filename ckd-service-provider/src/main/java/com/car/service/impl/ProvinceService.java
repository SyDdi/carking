package com.car.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.car.domain.Province;
import com.car.service.IProvinceService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IProvinceService")
public class ProvinceService extends BaseService<Province> implements IProvinceService {

    @Deprecated
    public Object provinceList() {
        Example example = new Example(Province.class);
        List<Province> list = this.selectByExample(example);
        List<JSONObject> result = new ArrayList<JSONObject>();
        for(Province p:list){
            JSONObject o = new JSONObject();
            o.put("id",p.getId());
            o.put("name",p.getProvinceName());
            result.add(o);
        }
        return result;
    }
}
