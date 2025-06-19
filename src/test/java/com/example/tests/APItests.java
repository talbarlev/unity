package com.example.tests;

import com.example.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class APItests extends BaseTest {

    @Test
    public void testAPI() {
        RestAssured.baseURI = "http://localhost:3000";

        String cookie = getSessionCookie();

        Response response = RestAssured.
                given()
                .multiPart("name", "Itay Ben Publisher")
                .multiPart("email", "כדככגעגכעגעגכעכג")
                .cookie("adminjs", cookie)
         .header("Cookie", cookie)
                .log().all()
                .when().post("/admin/api/resources/Publisher/actions/new");

        System.out.print("Response status " + response.getStatusCode() + " " + response.getBody().asString());
    }


    public static String getSessionCookie() {
        RestAssured.baseURI = "http://localhost:3000";

        Response response = given()
                .multiPart("email", "admin@example.com")
                .multiPart("password", "password")
                .when()
                .post("/admin/login")
                .then()
                .extract().response();

        String cookieValue = response.getCookie("adminjs");

        if (cookieValue == null) {
            throw new RuntimeException("❌ Login failed, cookie 'adminjs' not found.");
        }

        System.out.println("✅ Logged in. Cookie: " + cookieValue);
        return cookieValue;
    }
}
