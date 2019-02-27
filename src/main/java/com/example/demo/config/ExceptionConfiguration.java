package com.example.demo.config;

import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//TODO 这种形式是返回的modelAndView 在实际的开发过程中 用到的很少 需要返回json形式
//@Configuration
public class ExceptionConfiguration implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav = new ModelAndView();
        if(e instanceof ApplicationRunTimeExeption){
            ApplicationRunTimeExeption arte = (ApplicationRunTimeExeption) e;
            mav.addObject("code",arte.getInfoCode().getCode());
            mav.addObject("msg",arte.getInfoCode().getDesc());
        }else {
            mav.addObject("code", InfoCode.SERVICE_ORRER.getCode());
            mav.addObject("msg", InfoCode.SERVICE_ORRER.getDesc());
        }
        return mav;
    }
}
