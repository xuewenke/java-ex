
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util.time;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuewenke
 * @since 2020/11/29 下午3:28
 */
@UtilityClass
@Slf4j
public class DateUtil {

    private final SimpleDateFormat DATE_FORMAT_Y_M_D_H_M_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date toDate(String dateString) {
        try {
            return DATE_FORMAT_Y_M_D_H_M_S.parse(dateString);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(" 时间转换异常");
        }
    }
}
