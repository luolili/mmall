package com.mmall.util;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.mmall.service.impl.FileServiceImpl;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPUtil {
    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    private static String ftpIp = PropertyUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertyUtil.getProperty("ftp.user");
    private static String ftpPass = PropertyUtil.getProperty("ftp.pass");

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始链接");
        boolean isSuccess = ftpUtil.uploadFile("img", fileList);
        logger.info("上传结束");
        return isSuccess;
    }

    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //链接ftp server
        boolean isSuccess = connectServer(this.getIp(), this.getPort(), this.getUser(), this.getPwd());
        if (isSuccess) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //防止 乱码
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();//被动模式
                for (File file : fileList) {
                    fis = new FileInputStream(file);
                    ftpClient.storeFile(file.getName(), fis);
                }
            } catch (IOException e) {
                logger.info("上传文件出错", e);
                uploaded = false;
            } finally {
                assert fis != null;
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    private boolean connectServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.info("链接ftp server出错", e);
        }
        return isSuccess;
    }

    private FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
