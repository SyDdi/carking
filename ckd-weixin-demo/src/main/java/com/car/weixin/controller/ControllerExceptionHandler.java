package com.car.weixin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.exception.BusinessException;
import com.car.weixin.dto.ResponseResult;
import com.car.weixin.enums.ResponseErrorEnum;
import com.car.weixin.utils.RestResultGenerator;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice(basePackages = "com.car.weixin.controller")
@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handlerError(HttpServletRequest req, Exception e) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tip", "此错误说明调用接口失败，失败原因见msg，如果msg为空，联系后台");
        map.put("msg", e.getLocalizedMessage());
        map.put("path", req.getRequestURI());
        map.put("params", req.getParameterMap());
        map.put("status", "0");
        return map;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private Object globalExceptionHandler(BusinessException e) {
        LOGGER.error("--------->接口调用异常!", e);
        JSONObject o = new JSONObject();
        o.put("hello","world");
        return o;
    }



}
