


package com.x.atrs.util.net;

import lombok.Data;

/**
 * ftp/sftp 配置类
 *
 * @author xuewenke
 */
@Data
public class FtpConfig {
    private String host;
    private Integer port;
    private String userName;
    private String password;
    private String uploadPath;
}