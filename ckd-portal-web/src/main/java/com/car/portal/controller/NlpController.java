package com.car.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.car.domain.Province;
import com.car.domain.Zone;
import com.car.portal.utils.RestResultGenerator;
import com.car.service.IProvinceService;
import com.car.service.IZoneService;
import com.car.service.SensitiveWordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
@RestController
@RequestMapping(value = "/api/nlp")
public class NlpController {

    @Reference
    SensitiveWordService sensitiveWordService;

    /**
     * 过滤敏感词
     * @param text
     * @return
     */
    @RequestMapping(value = "/filter")
    public Object filter(@RequestParam("text") String text) {
        String txt = sensitiveWordService.filter(text,"***");
        return RestResultGenerator.genResult(txt,"成功!");
    }

}
