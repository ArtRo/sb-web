package com.example.demo.config;

import com.example.demo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

//@Configuration
public class FilterConfiguration {

//    @Bean
    public FilterRegistrationBean testFilter(){
        FilterRegistrationBean registerTestFilter = new FilterRegistrationBean();
        registerTestFilter.setFilter(new MyFilter());
        registerTestFilter.addInitParameter("paramName","paramName");
        registerTestFilter.addUrlPatterns("/*");
        return registerTestFilter;
    }
}
