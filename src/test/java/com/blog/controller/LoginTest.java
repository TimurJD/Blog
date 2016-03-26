package com.blog.controller;

import com.blog.db.DBManager;
import com.blog.util.PasswordUtil;
import org.bson.Document;
import org.junit.After;
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
        DBManager.INSTANCE.getDataBase().getCollection("users")
                .insertOne(new Document()
                            .append("email", "tim@g.com")
                            .append("password", PasswordUtil.hashPassword("123456")));
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
            .assertThat()
            .cookie("session")
            .body("message", equalTo("Successful logged in."));
    }

    @Test
    public void logedIn_WhenEmailUpperCase() {
        body = "{email: \"TIM@G.com\", password: \"123456\"}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .expect()
            .contentType(ContentType.JSON)
            .statusCode(200)
        .when()
            .post("/login")
        .then()
            .assertThat()
            .cookie("session")
            .body("message", equalTo("Successful logged in."));
    }

    @Test
    public void shouldBadRequest_whenBodyIsEmpty() {
        body = "";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .expect()
            .contentType(ContentType.JSON)
            .statusCode(400)
        .when()
            .post("/login")
        .then()
            .body("message", equalTo("Body cannot be empty."));
    }

    @Test
    public void shouldBadRequest_whenEmailLengthMoreThan25() {
        body = "{email: \"blablablablablablablablablablablablablablablabla@gmail.com\", password: \"123456\"}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .expect()
            .contentType(ContentType.JSON)
            .statusCode(400)
        .when()
            .post("/login")
        .then()
            .body("message", equalTo("Invalid user email."));
    }

    @Test
    public void shouldBadRequest_whenPasswordLengthLessThan6() {
        body = "{email: \"tim@g.com\", password: \"1234\"}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .expect()
            .contentType(ContentType.JSON)
            .statusCode(400)
        .when()
            .post("/login")
        .then()
            .body("message", equalTo("Invalid user password."));
    }

    @Test
    public void shouldBadRequest_whenPasswordLengthMoreThan15() {
        body = "{email: \"tim@g.com\", password: \"123456bbbsbbbbaskdbaskdjbaskdbaksdjbjbjbjbjbjjjaz\"}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .expect()
            .contentType(ContentType.JSON)
            .statusCode(400)
        .when()
            .post("/login")
        .then()
            .body("message", equalTo("Invalid user password."));
    }

    @After
    public void tearDown() {
        DBManager.INSTANCE.getDataBase().getCollection("sessions").drop();
    }
}