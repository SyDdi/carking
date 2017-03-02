package com.car.weixin.controller;

import com.car.core.utils.ImageUtil;
import com.car.exception.BusinessException;
import com.car.weixin.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/10.
 */
@Controller
public class MqController extends GenericController {

    @Resource
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/mq/test")
    public void test(HttpServletResponse response) throws Exception{
        long t = new Date().getTime();
        User u = new User();
        u.setOpenId("12345");
        amqpTemplate.convertAndSend("queueTestKey",u);
        response.getWriter().write(t+"");
    }

}
