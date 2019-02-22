package com.example.demo.util.scan;

import com.example.demo.entity.PrFuncs;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dhf_ndm on 2019/2/22
 * the previous bug derived from the previous
 */
@Component
public class ScanController {

    public void scanController() {
       String basePath = "com.example.demo.rest";
       String path = ScanController.class.getResource("/").getPath();
       String searchPath = path+basePath.replaceAll(",", File.separator);
       doScan(new File(searchPath));
    }

    private List<Class> doScan(File file) {
        if(file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File f : files){
                    doScan(f);
                }
            }else {
                if(isJavaFile(file)){
                    try {
                        Class<?> aClass = Class.forName(file.getName());
                        RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
                        if(null != annotation){
                            PrFuncs prFuncs = new PrFuncs();
                            prFuncs.setValue(annotation.value().toString());
                            prFuncs.setName(annotation.name());
                            Method[] methods = aClass.getDeclaredMethods();
                            if(null != methods && methods.length > 0){
                                for(Method m : methods){
                                    RequestMapping annotation1 = m.getAnnotation(RequestMapping.class);

                                }
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private boolean isJavaFile(File file) {
        return true;
    }
}
