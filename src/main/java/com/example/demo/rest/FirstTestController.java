package com.example.demo.rest;


import com.example.demo.entity.ThirdInfo;
import com.example.demo.service.ThirdInfoService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import com.example.demo.vo.BaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class FirstTestController {

    Logger logger = LoggerFactory.getLogger(FirstTestController.class);

    @Autowired
    ThirdInfoService thirdInfoService;

    @RequestMapping(value = "getOne")
    @ResponseBody
//    @Cacheable(value = "thirdInfo")
    public BaseVo getOne(Long id)throws ApplicationRunTimeExeption{
        logger.info("load1");
//        return new BaseVo(thirdInfoService.selectById(id));
        throw new ApplicationRunTimeExeption(InfoCode.SERVICE_ORRER);
    }

    @RequestMapping(value = "getwe")
    @ResponseBody
    public Integer getTwe(){
        return 1;
    }
}
