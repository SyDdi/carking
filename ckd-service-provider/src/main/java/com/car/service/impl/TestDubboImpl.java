package com.car.service.impl;


import com.car.service.TestDubbo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/4.
 */
@Service("testDubboService")
public class TestDubboImpl implements TestDubbo{
    @Override
    public String getStr(){
        return "haha 我是中文";
    }
}
