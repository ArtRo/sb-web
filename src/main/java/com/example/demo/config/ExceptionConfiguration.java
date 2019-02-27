package com.example.demo.config;

import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;


public class ExceptionConfiguration implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>();
        if(e instanceof ApplicationRunTimeExeption){
            ApplicationRunTimeExeption arte = (ApplicationRunTimeExeption) e;
            map.put("code",arte.getInfoCode().getCode());
            map.put("msg",arte.getInfoCode().getDesc());
        }else if(e instanceof org.springframework.security.access.AccessDeniedException ||
                e instanceof org.springframework.security.core.AuthenticationException){
            return null;
        }else {
            map.put("code", InfoCode.SERVICE_ORRER.getCode());
            map.put("msg", InfoCode.SERVICE_ORRER.getDesc());
        }
        mav.addAllObjects(map);
        return mav;
    }
}
