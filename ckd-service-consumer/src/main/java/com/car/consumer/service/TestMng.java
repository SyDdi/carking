package com.car.consumer.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.car.service.TestDubbo;
import org.springframework.stereotype.Service;



@Service
public class TestMng {

    @Reference
    TestDubbo testDubbo;//调用Dubbo暴露的接口

	public String getStr(){
//		return "TestMng";
		if(testDubbo==null) return "dubbo 接口为空";
		return testDubbo.getStr();
	}
	
}
