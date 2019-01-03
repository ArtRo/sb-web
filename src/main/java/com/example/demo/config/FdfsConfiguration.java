package com.example.demo.config;

import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.apache.logging.log4j.util.Strings;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
@PropertySource("classpath:app.properties")
@ConfigurationProperties(prefix = "fdfs")
public class FdfsConfiguration {

    Logger logger = LoggerFactory.getLogger(FdfsConfiguration.class);

    private String nginxIp;

    @Value("${fast.config.file.name:fdfs_client.properties}")
    String fdfsConfigName;

    private volatile boolean isInit = false;

    /*
      saveFile方法返回的是文件的绝对路径
      */
    public String saveFile(File file, String dir) throws ApplicationRunTimeExeption {
        if (file == null || file.length() < 1) {
            return null;
        }
        String s = null;
        try {
            s = saveFile(new FileInputStream(file), dir, getFileType(file.getName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ApplicationRunTimeExeption(InfoCode.FILE_NOT_FOUNT);
        }
        return s;
    }

    public String saveFile(MultipartFile file, String dir) throws ApplicationRunTimeExeption {
        if (file == null || file.getSize() < 1) {
            return null;
        }
        String s = null;
        try {
            s = saveFile(file.getInputStream(), dir, getFileType(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationRunTimeExeption(InfoCode.FILE_NOT_FOUNT);
        }
        return s;
    }

    public String saveFile(InputStream is, String dir, String fileExtension) throws ApplicationRunTimeExeption {
        TrackerServer trackerServer = null;
        try {
            byte fileBuf[] = null;
            if (is != null) {
                fileBuf = new byte[is.available()];
                is.read(fileBuf);
            }
            trackerServer = getTrackerServer();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            String[] fileIds = storageClient.upload_file(fileBuf, fileExtension, new NameValuePair[]{new NameValuePair("type", dir)});
            if (fileIds.length == 2) {
                if (Strings.isEmpty(nginxIp)) {
                    throw new Exception();
                }
                return "http://" + nginxIp + "/" + fileIds[0] + "/" + fileIds[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationRunTimeExeption(InfoCode.FILE_UPLOAD_FDFS_FAIL);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return null;
    }

    public boolean deleteFile(String fileUrl) throws ApplicationRunTimeExeption {
        TrackerServer trackerServer = null;
        try {
            if (Strings.isEmpty(nginxIp)) throw new Exception();
            if (fileUrl == null || fileUrl.length() < nginxIp.length() + 16) {
                return false;
            }
            String filePathName = fileUrl.substring(fileUrl.indexOf(nginxIp + "/") + (nginxIp + "/").length());
            String groupName = filePathName.substring(0, filePathName.indexOf("/"));
            String remoteFileName = filePathName.substring(filePathName.indexOf("/") + 1);
            trackerServer = getTrackerServer();
            StorageClient storageClient = new StorageClient(trackerServer,
                    null);
            int resultCode = storageClient.delete_file(groupName, remoteFileName);
            if (resultCode == 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("删除文件【" + fileUrl + "】失败:" + e.getMessage());
            throw new ApplicationRunTimeExeption(InfoCode.FILE_DELETE_FDFS_FAIL);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return false;
    }

    private void init() {
        try {
            ClientGlobal.initByProperties(fdfsConfigName);
            isInit = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeTrackerServer(TrackerServer trackerServer) {
        if (null != trackerServer) {
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TrackerServer getTrackerServer() {
        if (!isInit) {
            init();
        }
        TrackerClient client = new TrackerClient();
        try {
            TrackerServer server = client.getConnection();
            return server;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFileType(String fileName) {
        return Strings.isEmpty(fileName) ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getNginxIp() {
        return nginxIp;
    }

    public void setNginxIp(String nginxIp) {
        this.nginxIp = nginxIp;
    }
}
