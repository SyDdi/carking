package com.car.mp.annotation;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/9/20.
 *
 * 可以使用在Controller或单独某个方法上。最好给每个name都定义单独的name，默认all的范围太广，使用方法如下:
 *
     @Controller
     @RequestMapping("/demo")
     @Frequency(name="demo", limit=3, time=1)
     public class DemoController {
         @RequestMapping(value = {"index"})
         @Frequency(name="method", limit=3, time=1)
         public void method()

     }
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Frequency {

    String name() default "all";
    int time()  default 0;//单秒：位
    int limit()  default 0;//单位：次数
}
