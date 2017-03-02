package com.car.mp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:applicationContext-springmvc.xml" })
public class BasicTest {
    @Test
    public void test() {

    }
}
