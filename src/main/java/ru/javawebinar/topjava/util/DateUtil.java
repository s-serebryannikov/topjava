package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public final class DateUtil {
    public DateUtil() {
    }
    public static String formatLDTToString(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime formatStringToLDT(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }
}
