
package com.seven.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author xuewenke
 */
@Slf4j
public class FtpUtil {

    private FtpUtil(){}

    /**
     * ftp 文件上传
     * @param config
     * @param uploadFile
     * @param remoteFileName
     * @return
     */
    public static boolean uploadFile(FtpConfig config, File uploadFile, String remoteFileName) {
        FTPClient ftpClient = createFtpClientInstance(config);
        try(FileInputStream input = new FileInputStream(uploadFile)) {
            remoteFileName = resetCharset(remoteFileName);
            boolean res = ftpClient.storeFile(remoteFileName, input);
            return res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                ftpClient.logout();
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        return false;
    }


    /**
     * 重置字符集
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String resetCharset(String fileName) throws UnsupportedEncodingException {
        return new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1);
    }

    private static FTPClient createFtpClientInstance(FtpConfig config) {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(config.getHost(), config.getPort());
            ftpClient.login(config.getUserName(), config.getPassword());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            int replyCode = ftpClient.getReplyCode();
            log.info("replyCode = [{}]", replyCode);
            if (FTPReply.isNegativePermanent(replyCode)) {
                throw new RuntimeException("ftp客户端登录失败");
            }
            return ftpClient;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("创建ftp客户端失败");
        }
    }


}