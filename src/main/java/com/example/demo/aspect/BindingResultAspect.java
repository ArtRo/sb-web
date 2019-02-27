package com.example.demo.aspect;

import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import com.example.demo.vo.BaseVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
@Aspect
@Component
@Order(1)
public class BindingResultAspect {

    @Pointcut("execution(public * com.example.demo.rest.*.*(..))")
    public void BindingResult() {
    }

//    @Before("BindingResult()")
//    public void before(JoinPoint joinPoint) throws Throwable{
//        System.out.println(joinPoint);
//    }

//    @After("BindingResult()")
//    public void after(JoinPoint joinPoint)throws Throwable{
//        System.out.println(" aspect has done "+System.currentTimeMillis());
//    }

    @Around("BindingResult()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        Object[] args = joinPoint.getArgs();
        if(args.length > 0){
            for(Object a : args){
                if(a instanceof BindingResult){
                    BindingResult br = (BindingResult) a;
                    if(br.hasErrors()){
                       throw new ApplicationRunTimeExeption(InfoCode.PARAMETER_ERROR);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}
