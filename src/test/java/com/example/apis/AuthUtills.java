package com.example.apis;

import com.example.data.common.Properties;
import com.example.utills.TestLogger;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUtills {
    final static String BASE_URL = Properties.BASEURL;
    final static String EMAIL = Properties.ADMIN_USERNAME;
    final static String PASSWORD = Properties.ADMIN_PASSWORD;
    final static String ADMIN_JS_COOKIE = "adminjs";
    final static String ENDPOINT = "login";

    public static String getSessionCookie() {
        String fullUrl = BASE_URL + ENDPOINT;

        Response response = given()
                .multiPart("email", EMAIL)
                .multiPart("password", PASSWORD)
                .when()
                .post(fullUrl);

        if (response.statusCode() != 302 && response.statusCode() != 200) {
            TestLogger.error("❌ TOKEN failed. Status code: " + response.statusCode());
            throw new RuntimeException("❌ TOKEN failed. Status code: " + response.statusCode());
        }

        String cookieValue = response.getCookie(ADMIN_JS_COOKIE);

        if (cookieValue == null) {
            TestLogger.error("❌ TOKEN failed, cookie" + ADMIN_JS_COOKIE + "not found.");

            throw new RuntimeException("❌ TOKEN failed, cookie" + ADMIN_JS_COOKIE + "not found.");
        }

        TestLogger.info("✅ TOKEN. Cookie: " + cookieValue);

        return cookieValue;
    }
}
