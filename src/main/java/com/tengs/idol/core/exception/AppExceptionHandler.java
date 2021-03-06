package com.tengs.idol.core.exception;

import com.tengs.idol.model.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);
    /**
     * 处理不带任何注解的参数绑定校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse handleBingException(BindException e) {
        String errorMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> ((FieldError)objectError).getField() + ((FieldError)objectError).getDefaultMessage())
                .collect(Collectors.joining(","));
        //"errorMsg": "name不能为空,age最小不能小于18"
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("000001");
        baseResponse.setErrmsg(errorMsg);
        return baseResponse;
    }

    /**
     * 处理 @RequestBody参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> ((FieldError)objectError).getField() + ((FieldError)objectError).getDefaultMessage())
                .collect(Collectors.joining(","));
        //"errorMsg": "name不能为空,age最小不能小于18"
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("000001");
        baseResponse.setErrmsg(errorMsg);
        return baseResponse;
    }

    @ExceptionHandler(BzException.class)
    public BaseResponse handleBzException(BzException e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(e.getCode());
        baseResponse.setErrmsg(e.getMsg());
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse handleBzException(Exception e) {
        logger.info(e.getMessage());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("999999");
        baseResponse.setErrmsg("系统错误");
        return baseResponse;
    }
}
