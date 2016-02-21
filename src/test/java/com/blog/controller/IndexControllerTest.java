package com.blog.controller;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.port;
import static com.jayway.restassured.RestAssured.when;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;

/**
 * @author Timur Berezhnoi
 */
public class IndexControllerTest {
	
	@BeforeClass
	public static void setUp() {
		baseURI = "http://localhost:7373";
		port = 7373;
	}
	
	@Test
	public void statusShouldBe200() {
	    when()
	    	.get("/")
	    .then()
		    .contentType(ContentType.HTML)
	    	.statusCode(200);
	}
}