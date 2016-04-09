package com.blog.controller;

import com.jayway.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;

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