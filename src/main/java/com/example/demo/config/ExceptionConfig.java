package com.example.demo.config;

import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//FIXME 全局异常处理 全局异常处理和spring security 冲突 在配置的时候一定要协商好配置的框架
//@ControllerAdvice
public class ExceptionConfig {

    Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionAppHandler(Exception ex) {

        HashMap<String, Object> mav = new HashMap<>();
        if (ex instanceof ApplicationRunTimeExeption) {
            ApplicationRunTimeExeption arte = (ApplicationRunTimeExeption) ex;
            mav.put("code", arte.getInfoCode().getCode());
            mav.put("msg", arte.getInfoCode().getDesc());
        } else if(ex instanceof AccessDeniedException){
            return null;
        } else if(ex instanceof AuthenticationException){
            return null;
        } else {
            ex.printStackTrace();
            mav.put("code", InfoCode.SERVICE_ORRER.getCode());
            mav.put("msg", InfoCode.SERVICE_ORRER.getDesc());
        }
        return mav;
    }

}
