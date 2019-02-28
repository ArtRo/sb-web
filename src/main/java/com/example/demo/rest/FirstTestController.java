package com.example.demo.rest;


import com.example.demo.bo.Mobile;
import com.example.demo.config.FdfsConfiguration;
import com.example.demo.entity.PrFuncs;
import com.example.demo.entity.ThirdInfo;
import com.example.demo.service.PrFuncsService;
import com.example.demo.service.ThirdInfoService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import com.example.demo.validator.NotNull;
import com.example.demo.vo.BaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

@Controller
@RequestMapping(value = "/test")
public class FirstTestController {

    Logger logger = LoggerFactory.getLogger(FirstTestController.class);

    @Autowired
    ThirdInfoService thirdInfoService;

    @Autowired
    PrFuncsService prFuncsService;

    @Autowired
    FdfsConfiguration fdfsConfiguration;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ResponseBody
//    @PreAuthorize("hasAuthority('adddd')")
//    @Cacheable(value = "thirdInfo")
    public BaseVo getOne(Long id)throws ApplicationRunTimeExeption{
        logger.info("load12222222222222");
//        return new BaseVo(thirdInfoService.selectById(id));
        throw new ApplicationRunTimeExeption(InfoCode.SERVICE_ORRER);
    }

    @RequestMapping(value = "getwe")
    @ResponseBody
    public Integer getTwe(@Validated Mobile mobile, BindingResult result){
        PrFuncs prFuncs = new PrFuncs();
        prFuncs.setFuncName("adfasdsa");
        prFuncs.setFuncValue("asdfa");
        prFuncs.setRequestUrl("/adsd");
        prFuncs.setParentId(1);
        return prFuncsService.insert(prFuncs);
    }


    @RequestMapping(value = "fdfs_upload")
    @ResponseBody
    public String fdfsUpload() throws ApplicationRunTimeExeption{
        File file = new File("E:\\flash.txt");
        return fdfsConfiguration.saveFile(file,file.getParent());
    }

    @RequestMapping(value = "fdfs_delte")
    @ResponseBody
    public boolean fdfsDelete(String file_url)throws ApplicationRunTimeExeption{
        return fdfsConfiguration.deleteFile(file_url);
    }
}
