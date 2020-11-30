

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
