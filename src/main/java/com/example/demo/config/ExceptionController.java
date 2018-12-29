package com.example.demo.config;

import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> exceptionHandler(Exception ex) {
        logger.info("======second go filter");
        HashMap<String, Object> mav = new HashMap<>();
        if (ex instanceof ApplicationRunTimeExeption) {
            ApplicationRunTimeExeption arte = (ApplicationRunTimeExeption) ex;
            mav.put("code", arte.getInfoCode().getCode());
            mav.put("msg", arte.getInfoCode().getDesc());
        } else if(null != ex){
            ex.printStackTrace();
            mav.put("code", InfoCode.SERVICE_ORRER.getCode());
            mav.put("msg", InfoCode.SERVICE_ORRER.getDesc());
        }
        return mav;
    }
}
