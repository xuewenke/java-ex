
package com.x.atrs.util.time;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xuewenke
 */
@UtilityClass
public class LocalDateUtil {

    public final String pattern = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter PATTERN_YMD_HMS = DateTimeFormatter.ofPattern(pattern);

    public long now() {
        return System.currentTimeMillis();
    }

    public Date longToDate(Long timestamp) {
        return new Date(timestamp);
    }

    public LocalDate longToLocalDate(Long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
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

    public LocalDateTime toLocalDateTime(@Nonnull Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public LocalDateTime longToLocalDateTime(Long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public LocalDateTime lastSecondToday() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    }

    public LocalTime lastSecond() {
        return LocalTime.of(23, 59, 59);
    }

    public String toTimeStr(Long timestamp) {
        return Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
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

    /**
     * 给定一个时间区间，随机返回一个区间内的时间
     *
     * @param begin
     * @param end
     * @return
     */
    public LocalTime getRandomBetween(LocalTime begin, LocalTime end) {
        long untilMinutesChange = begin.until(end, ChronoUnit.MINUTES);
        long randomMinutes = ThreadLocalRandom.current().nextLong(untilMinutesChange);
        return begin.plusMinutes(randomMinutes);
    }

}