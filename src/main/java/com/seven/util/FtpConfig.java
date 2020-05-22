
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.seven.util;

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