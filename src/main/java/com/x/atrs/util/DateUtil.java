
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author xuewenke
 */
@UtilityClass
public class DateUtil {

    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * timeStr yyyy-MM-dd HH:mm:ss
     * 不做显示异常是为了对调用方友好，但是真除了错误还是要中断程序的。
     * 切忌不要返回null。万一调用放直接使用你的null值，但没有报错的情况下，就会造成业务数据的异常。
     * 比如创建时间，修改时间，要是数据库允许为空的话，就会有问题了。
     *
     * @return
     */
    public Date toDate(String timeStr) {
        try {
            return sdf.parse(timeStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("传入参数错误：" + timeStr);
        }
    }

    public LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDate();
    }

    public void main(String[] args) {
        System.out.println(toLocalDate(new Date()).getMonthValue());
    }
}