package com.example.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    public static String generateTimestampDetailed() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String generateUniqueName(String prefix) {
        return prefix + "_" + generateTimestamp() + randonNumber(1000, 9999);
    }

    public static String generateUniqueEmail(String prefix) {
        return prefix + "_" + generateTimestamp() + "@example.com";
    }

    public static Integer randonNumber(Integer min, Integer max) {
        return  new Random().nextInt(max - min + 1) + min;

    }
}
