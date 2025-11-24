package com.expensetracker.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate parse(String s) {
        if (s == null || s.trim().isEmpty()) return LocalDate.now();
        return LocalDate.parse(s);
    }
    public static String currentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
}
