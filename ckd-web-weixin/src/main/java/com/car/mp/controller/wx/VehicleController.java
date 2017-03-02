package com.car.mp.controller.wx;


import com.alibaba.dubbo.config.annotation.Reference;
import com.car.mp.controller.GenericController;
import com.car.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/11/10.
 */
@Controller
public class VehicleController extends GenericController {

    @Reference
    IUserService userService;

    @RequestMapping(value = "/vehicle/detail.html", method = RequestMethod.GET)
    public String detail(ModelMap modelMap, HttpServletRequest request) {

        System.out.println(userService.selectByOpenId("11212"));

        return "vehicle/detail";
    }




}
