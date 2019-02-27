package com.example.demo;

import com.example.demo.config.FdfsConfiguration;
import com.example.demo.filter.MyFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
@EnableCaching
//@ServletComponentScan(basePackages = {"com.example.demo.filter"})
@EnableConfigurationProperties({FdfsConfiguration.class})
public class DemoApplication {


    static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    public static void main(String[] args) {

        logger.debug("application starting");

        SpringApplication.run(DemoApplication.class, args);
    }

}

