
/*
 * @Copyright 2014-2020 云安宝 (www.yunanbao.com.cn).
 *
 * 注意：本内容仅限于深圳云安宝科技有限公司内部传阅，禁止外泄以及用于其他商业目的
 *
 */

package com.x.atrs.util;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

    private final String pattern = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter PATTERN_YMD_HMS = DateTimeFormatter.ofPattern(pattern);

    public long now() {
        return System.currentTimeMillis();
    }

    public Date longToDate(Long timestamp) {
        return new Date(timestamp);
    }

    public LocalDate longToLocalDate(Long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.systemDefault()).toLocalDate();
    }

    /**
     * timesString 为null，或是格式不对则报错
     *
     * @param timesString 如下格式：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public LocalDateTime toLocalDateTime(@Nonnull String timesString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timesString, formatter);
    }

    public LocalDateTime longToLocalDateTime(Long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.systemDefault()).toLocalDateTime();
    }

    public LocalDateTime lastSecondToday() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    }

    public LocalTime lastSecond() {
        return LocalTime.of(23, 59, 59);
    }

    public String toTimeStr(Long timestamp) {
        return Instant.ofEpochMilli(timestamp)
                .atZone(ZoneOffset.systemDefault())
                .toLocalDateTime()
                .format(PATTERN_YMD_HMS);
    }

    /**
     * 时间格式 yyyy-MM-dd hh:mm:ss
     *
     * @param localDateTime 如果为null，则报错
     * @return yyyy-MM-dd hh:mm:ss
     */
    public String toTimeStr(@Nonnull LocalDateTime localDateTime) {
        return localDateTime.format(PATTERN_YMD_HMS);
    }

}