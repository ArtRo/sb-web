package com.example.demo.util.scan;

import com.example.demo.dao.PrFuncsDao;
import com.example.demo.entity.PrFuncs;
import com.example.demo.service.PrFuncsService;
import com.example.demo.validator.OpenInterFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhf_ndm on 2019/2/22
 * the previous bug derived from the previous
 */
@Component
public class ScanController {

    @Autowired
    PrFuncsService prFuncsService;

    public void scanController() {
        String basePath = "com.example.demo.rest";
        String path = ScanController.class.getResource("/").getPath();
        String searchPath = path + basePath.replaceAll(",", File.separator);
        doScan(new File(searchPath));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void doScan(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    doScan(f);
                }
            } else {
                if (isJavaFile(file)) {
                    try {
                        //使用 PreAuthorize 时 只有方法被RequstMapping 以及 同时注解时 才扫描入库
                        //目前不适用PreAuthorize  而是在过滤器里判断 有没有权限
                        Class<?> aClass = Class.forName(file.getName());
                        //当接口不是开放接口的时候 需要扫描入库 配置权限
                        if(null == aClass.getAnnotation(OpenInterFace.class)){
                            RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
                            if (null != annotation) {
                                PrFuncs prFunc = new PrFuncs();
                                prFunc.setRequestUrl(annotation.value().toString());
                                prFunc.setFuncName(annotation.name());
                                prFunc.setParentId(1);
                                prFunc.setFuncValue("");
                                int insert = prFuncsService.insert(prFunc);
                                List<PrFuncs> prFuncs = new ArrayList<>();
                                Method[] methods = aClass.getDeclaredMethods();
                                if (null != methods && methods.length > 0) {
                                    for (Method m : methods) {
                                        RequestMapping annotation1 = m.getAnnotation(RequestMapping.class);
                                        OpenInterFace annotation2 = m.getAnnotation(OpenInterFace.class);
                                        if(null!=annotation1 && null == annotation2){
                                            PrFuncs prFunc1 = new PrFuncs();
                                            prFunc1.setFuncName(annotation1.name());
                                            String requestUrl = annotation.value()+"/"+annotation1.value();
                                            prFunc1.setRequestUrl(requestUrl.replaceAll("/+","/"));
                                            prFunc1.setFuncValue("");
                                            prFunc1.setParentId(insert);
                                            prFuncs.add(prFunc1);
                                        }
                                    }
                                }
                                if(prFuncs.size() > 0){
                                    int[] ints = prFuncsService.insertByBatch(prFuncs);
                                }
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean isJavaFile(File file) {
        if (file.exists()) {
            String name = file.getName();
            int i = name.lastIndexOf(".");
            return ".java".equals(name.substring(i)) ? true : false;
        }
        return false;
    }
}
