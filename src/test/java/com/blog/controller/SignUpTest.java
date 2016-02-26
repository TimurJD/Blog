package com.blog.controller;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;

import org.bson.Document;
import org.junit.*;

import com.blog.db.DBManager;
import com.jayway.restassured.http.ContentType;

/**
 * @author Timur Berezhnoi
 */
public class SignUpTest {

	private String body;
	
	@BeforeClass
	public static void setUp() {
        DBManager.INSTANCE.getDataBase();
		baseURI = "http://localhost:7373";
		port = 7373;
	}
	
	@Test
	public void statusShouldBe201_whenSignUpValid() {
		body = "{email: \"tim@g.com\", name: \"Timur\", password: \"123456\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(201)
		.when()
	    	.post("/signup")
	    .then()
	    	.body("message", equalTo("Succesfully signedUp"));
	}
	
	@Test
	public void shouldBadRequest_whenEmailInvalid() {
		body = "{email: \"blabla\", name: \"Timur\", password: \"123456\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user email."));;
	}
	
	@Test
	public void shouldBadRequest_whenEmailLengthMoreThan25() {
		body = "{email: \"blablablablablablablablablablablablablablablabla@gmail.com\", name: \"Timur\", password: \"123456\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user email."));;
	}
	
	@Test
	public void shouldBadRequest_whenNameInvalid() {
		body = "{email: \"somemail@gmail.com\", name: \"Ti?!mur\", password: \"123456\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user name."));;
	}
	
	@Test
	public void shouldBadRequest_whenNameLengthMoreThan20() {
		body = "{email: \"someema@gmail.com\", name: \"TimurTimurTimurTimurTimurTimurTimurTimurTimurTimurTimur\", password: \"123456\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user name."));;
	}
	
	@Test
	public void shouldBadRequest_whenPasswordLengthMoreThan15() {
		body = "{email: \"soil@gmail.com\", name: \"Timur\", password: \"123456789012345675432123\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user password."));;
	}
	
	@Test
	public void shouldBadRequest_whenPasswordLengthLessThan6() {
		body = "{email: \"somells@gmail.com\", name: \"Timur\", password: \"123456789012345675432123\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user password."));;
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
			.post("/signup")
		.then()
	    	.body("message", equalTo("Body cannot be empty."));
	}
	
	@Test
	public void shouldBadRequest_whenOnKeyIsMissing() {
		body = "{name: \"Timur\", password: \"123456789012345675432123\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user email."));
	}
	
	@Test
	public void shouldBadRequest_whenOnElseKeyIsMissing() {
		body = "{email: \"siliiii@gmail.com\", password: \"123456789012345675432123\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user name."));
	}
	
	@Test
	public void shouldBadRequest_whenUserAlredyExists() {
		
		DBManager.INSTANCE.getDataBase().getCollection("users")
			.insertOne(new Document().append("email", "asd@gmail.com").append("name", "Timur").append("password", "123453"));
		
		body = "{email: \"asd@gmail.com\", name: \"Timur\", password: \"123453\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid email or user with the email is alredy in use!"));
	}
	
	@After
	public void tearDown() {
		DBManager.INSTANCE.getDataBase().getCollection("users").drop();
	}
}