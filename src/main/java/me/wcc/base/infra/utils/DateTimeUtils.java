package me.wcc.base.infra.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
public class DateTimeUtils {
    private DateTimeUtils() {
    }

    public static Long secondsBetween(LocalDateTime start, LocalDateTime end) {
        return secondsBetween(start.atZone(ZoneId.systemDefault()).toInstant(), end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long secondsBetween(Instant start, Instant end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("End time must greater than start time!");
        }
        return end.getEpochSecond() - start.getEpochSecond();
    }

    public static Long getNanoTime(Instant instant) {
        return instant.toEpochMilli() * 1000000;
    }

    public static Long getNanoTime(LocalDateTime localDateTime) {
        return getNanoTime(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long getNanoTime(Date date) {
        return getNanoTime(date.toInstant());
    }
}
