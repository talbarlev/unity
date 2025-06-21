package com.example.utills;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Reporter;

public class TestLogger {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    private TestLogger() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void log(String message) {
        String timestamp = DataGenerator.generateTimestampDetailed();
        Reporter.log("[" + timestamp + "] " + message, true);
    }

    public static void info(String message) {
        log("INFO: " + message);
    }

    public static void error(String message) {
        log("❌ ERROR: " + message);
    }

    public static void step(String message) {
        log("➡️ " + message);
    }

    public static void json(Object obj, String title) {
        try {
            String prettyJson = gson.toJson(obj);
            log("📦 " + title + ":\n" + prettyJson);
        } catch (Exception e) {
            error("⚠️ Failed to serialize object to JSON: " + e.getMessage());
        }
    }

    public static void warning(String message) {
        log("⚠️ WARNING: " + message);
    }
}