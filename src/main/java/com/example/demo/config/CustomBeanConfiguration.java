package com.example.demo.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
//TODO 注入一些常用的Bean
@Configuration
public class CustomBeanConfiguration {

    @Bean
    public Gson getGson(){
        return new Gson();
    }

    @Bean
    public ExceptionConfiguration getExceptionConfiguration(){
        return new ExceptionConfiguration();
    }
}
