package com.example.utills;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Allure;
import org.testng.Reporter;

public class TestLogger {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final boolean USE_REPORTER_LOG = true;


    private TestLogger() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static void logAndAllure(String prefix, String message, boolean isStep) {
        String timestamp = DataGenerator.generateTimestampDetailed();
        String finalMessage = "[" + timestamp + "] " + prefix + message;

        Reporter.log(finalMessage, true);

        if (isStep) {
            Allure.step(message);
        }
    }

    public static void info(String message) {
        logAndAllure("INFO: ", message, false);
    }

    public static void warning(String message) {
        logAndAllure("⚠️ WARNING: ", message, false);
    }

    public static void error(String message) {
        logAndAllure("❌ ERROR: ", message, false);
    }

    public static void step(String message) {
        logAndAllure("➡️ ", message, true);
    }

    public static void json(Object obj, String title) {
        try {
            String prettyJson = gson.toJson(obj);
            String fullMessage = "📦 " + title + ":\n" + prettyJson;
            logAndAllure("", fullMessage, false);

            Allure.addAttachment(title, "application/json", prettyJson, ".json");
        } catch (Exception e) {
            error("⚠️ Failed to serialize object to JSON: " + e.getMessage());
        }
    }
}
