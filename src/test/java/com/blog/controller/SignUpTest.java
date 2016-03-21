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
		body = "{email: \"tim@g.com\", firstName: \"Timur\", lastName: \"Somename\", password: \"123456\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(201)
		.when()
	    	.post("/signup")
	    .then()
	    	.body("message", equalTo("Successfully signedUp"));
	}

	@Test
	public void shouldBadRequest_whenEmailInvalid() {
		body = "{email: \"blabla\", firstName: \"Timur\", lastName: \"LastName\", password: \"123456\"}";
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
	public void shouldBadRequest_whenEmailLengthMoreThan25() {
		body = "{email: \"blablablablablablablablablablablablablablablabla@gmail.com\", firstName: \"Timur\", lastName: \"LastName\", password: \"123456\"}";
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
	public void shouldBadRequest_whenFNameInvalid() {
		body = "{email: \"somemail@gmail.com\", firstName: \"Ti?!mur\", lastName: \"LastName\", password: \"123456\"}";
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
	public void shouldBadRequest_whenFNameLengthMoreThan20() {
		body = "{email: \"someema@gmail.com\", firstName: \"TimurTimurTimurTimurTimurTimurTimurTimurTimurTimurTimur\", lastName: \"LastName\", password: \"123456\"}";
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
    public void shouldBadRequest_whenLNameInvalid() {
        body = "{email: \"somemail@gmail.com\", firstName: \"Tisamur\", lastName: \"Last?1Name\", password: \"123456\"}";
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
    public void shouldBadRequest_whenLNameLengthMoreThan20() {
        body = "{email: \"someema@gmail.com\", firstName: \"Timur\", lastName: \"LastNameLastNameLastNameLastNameLastNameLastNameLastNameLastNameLastName\", password: \"123456\"}";
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
	public void shouldBadRequest_whenPasswordLengthMoreThan15() {
		body = "{email: \"soil@gmail.com\", firstName: \"Timur\", lastName: \"BlaBla\", password: \"123456789012345675432123\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user password."));
	}

	@Test
	public void shouldBadRequest_whenPasswordLengthLessThan6() {
		body = "{email: \"somells@gmail.com\", firstName: \"Timur\", lastName: \"LastName\", password: \"123456789012345675432123\"}";
		given()
			.contentType(ContentType.JSON)
			.body(body)
		.expect()
			.contentType(ContentType.JSON)
			.statusCode(400)
		.when()
			.post("/signup")
		.then()
	    	.body("message", equalTo("Invalid user password."));
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
		body = "{firstName: \"Timur\", password: \"123456789012345675432123\"}";
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
	public void shouldBadRequest_whenOneElseKeyIsMissing() {
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
			.insertOne(new Document().append("email", "asd@gmail.com").append("firstName", "Timur")
                        .append("lastName", "LastName").append("password", "123453"));

		body = "{email: \"asd@gmail.com\", firstName: \"Timur\", lastName: \"LastName\", password: \"123453\"}";
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
