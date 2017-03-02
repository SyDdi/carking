package com.car.mp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.User;
import com.car.mp.config.ApplicationConfig;
import com.car.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/11/10.
 */
@Controller
public class UserController {

    //@Autowired //放在provider那台机器上，结果正确
    @Reference //在consumer端调用getId() 就是null
    IUserService userService;

    @RequestMapping(value = "/user/insert")
    @ResponseBody
    public Object insert() {
        User user = new User();
        user.setNickName("march");
        user = userService.insert(user);
        System.out.println("测试.....userID="+user.getId());
        return user;
    }




}
