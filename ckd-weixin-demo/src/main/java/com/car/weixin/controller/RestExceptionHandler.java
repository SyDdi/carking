package com.car.weixin.controller;



import com.car.weixin.dto.ResponseResult;
import com.car.weixin.enums.ResponseErrorEnum;
import com.car.weixin.utils.RestResultGenerator;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.UnexpectedTypeException;

/**
 * RestController全局异常处理器
 */
//@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);

    /**
     * 统一的rest接口异常处理器
     *
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private <T> ResponseResult<T> globalExceptionHandler(Exception e) {

        LOGGER.error("--------->接口调用异常!", e);
        e.printStackTrace();
        return RestResultGenerator.genErrorResult(ResponseErrorEnum.INTERNAL_SERVER_ERROR);
    }

//    /**
//     * bean校验未通过异常
//     *
//     * @see javax.validation.Valid
//     * @see org.springframework.validation.Validator
//     * @see org.springframework.validation.DataBinder
//     * @ExceptionHandler(value = { IOException.class , RuntimeException.class })
//     */
//    @ExceptionHandler( value = { UnexpectedTypeException.class , MissingServletRequestParameterException.class })
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    private <T> ResponseResult<T> illegalParamsExceptionHandler(UnexpectedTypeException e) {
//
//        LOGGER.error("--------->请求参数不合法!", e);
//        return RestResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS);
//    }

}
