package com.example.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataGenerator {

    public static String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static String generateUniqueName(String prefix) {
        return prefix + "_" + generateTimestamp();
    }

    public static String generateUniqueEmail(String prefix) {
        return prefix + "_" + generateTimestamp() + "@example.com";
    }
}
