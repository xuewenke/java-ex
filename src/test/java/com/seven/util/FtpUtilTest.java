
package com.seven.util;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

/**
 *
 * @author xuewenke
 */
public class FtpUtilTest {


    private  FtpConfig config;

    @BeforeTest
    public void intiConfig() {
        FtpConfig config = new FtpConfig();
        config.setHost("192.168.1.35");
        config.setUserName("administrator");
        config.setPort(21);
        config.setPassword("Admin_12345");
        this.config = config;
    }

    @Test
    public void testUploadFile2() {
        // files-WhatsApp.dmg
        File file = new File("/Users/xuewenke/Downloads/test.dmg");
        boolean res = FtpUtil.uploadFile(config, file, "测试.log");
        System.out.println(
                res
        );
    }
}