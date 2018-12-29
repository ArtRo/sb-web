package com.example.demo.config;

import com.example.demo.interactor.CommonInteractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InteractorConfiguration implements WebMvcConfigurer {

    @Resource
    CommonInteractor commonInteractor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInteractor).addPathPatterns("/**");
    }
}
