package com.blog.controller;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.http.ContentType;

/**
 * @author Timur Berezhnoi
 */
public class LoginTest {

    private String body;

    @BeforeClass
    public static void setUp() {
        baseURI = "http://localhost:7373";
        port = 7373;
    }

    @Test
    public void shouldSuccessfullyLoggedIn() {
        body = "{email: \"tim@g.com\", password: \"123456\"}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .expect()
            .contentType(ContentType.JSON)
            .statusCode(200)
        .when()
            .post("/login")
        .then()
            .body("message", equalTo("Successful logged in."));
    }
}