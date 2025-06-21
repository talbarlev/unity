package com.example.apis;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUtills {
     final static String BASE_URL = "http://localhost:3000";
     final static String EMAIL = "admin@example.com";
     final static String PASSWORD = "password";
     final static String ADMIN_JS_COOKIE = "adminjs";
     final static String ENDPOINT = "/admin/login";

    public static String getSessionCookie() {
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .multiPart("email", EMAIL)
                .multiPart("password", PASSWORD)
                .when()
                .post(ENDPOINT);

        if (response.statusCode() != 302 && response.statusCode() != 200) {
            throw new RuntimeException("❌ TOKEN failed. Status code: " + response.statusCode());
        }

        String cookieValue = response.getCookie(ADMIN_JS_COOKIE);

        if (cookieValue == null) {
            throw new RuntimeException("❌ TOKEN failed, cookie 'adminjs' not found.");
        }

        System.out.println("✅ TOKEN. Cookie: " + cookieValue);

        return cookieValue;
    }
}
