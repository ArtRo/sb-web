package com.example.demo.util.scan;

import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.service.PrFuncsService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.validator.OpenInterFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhf_ndm on 2019/2/22
 * the previous bug derived from the previous
 */
//@Component
public class ScanController implements ApplicationRunner {

    @Autowired
    PrFuncsService prFuncsService;

    @Transactional(rollbackFor = ApplicationRunTimeExeption.class,propagation = Propagation.REQUIRED)
    public void scanController() {
        String basePath = "com.example.demo.rest";
        String path = ScanController.class.getResource("/").getPath();
        String searchPath = path + basePath.replaceAll("\\.", "\\\\");
        try {
            doScan(basePath, searchPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationRunTimeExeption("failed");
        }
    }


    public void doScan(String pkePath, String path)throws Exception {
        File pathFile = new File(path);
        if (pathFile.exists() && pathFile.isDirectory()) {
            File[] files = pathFile.listFiles(pathname -> {
                return pathname.isDirectory() || pathname.getName().endsWith("class");
            });
            for (File f : files) {
                if (f.isDirectory()) {
                    doScan(pkePath + "." + f.getName(), path + "/" + f.getName());
                    continue;
                }
                try {
                    //使用 PreAuthorize 时 只有方法被RequstMapping 以及 同时注解时 才扫描入库
                    //目前不适用PreAuthorize  而是在过滤器里判断 有没有权限
                    String filePath = pkePath+"."+f.getName();
                    Class<?> aClass = Class.forName(filePath.substring(0, filePath.length() - 6));
                    //当接口不是开放接口的时候 需要扫描入库 配置权限
                    if (null == aClass.getAnnotation(OpenInterFace.class)) {
                        RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
                        if (null != annotation) {
                            PrFuncs prFunc = new PrFuncs();
                            prFunc.setRequestUrl(annotation.value()[0]);
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
                                    if (null != annotation1 && null == annotation2) {
                                        PrFuncs prFunc1 = new PrFuncs();
                                        prFunc1.setFuncName(annotation1.name());
                                        String requestUrl = annotation.value()[0] + "/" + annotation1.value()[0];
                                        prFunc1.setRequestUrl(requestUrl.replaceAll("/+", "/"));
                                        prFunc1.setFuncValue("");
                                        prFunc1.setParentId(Math.toIntExact(prFunc.getId()));
                                        prFuncs.add(prFunc1);
                                    }
                                }
                            }

                            if (prFuncs.size() > 0) {
                                Integer ints = prFuncsService.insertByBatch(prFuncs);
                                if(null == ints || !ints.equals(prFuncs.size())){
                                    throw new ApplicationRunTimeExeption("批量插入失败");
                                }
                            }
                        }
                    }
                    throw new ApplicationRunTimeExeption("failed");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ApplicationRunTimeExeption("批量导入方法失败");
                }
            }
        }
    }


    private boolean isJavaFile(File file) {
        if (file.exists()) {
            String name = file.getName();
            int i = name.lastIndexOf(".");
            return ".class".equals(name.substring(i)) ? true : false;
        }
        return false;
    }

    private byte[] getClassBytes(File file) throws Exception {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);
        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        scanController();
    }



}
