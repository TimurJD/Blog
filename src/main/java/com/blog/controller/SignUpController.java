package com.blog.controller;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.halt;
import static spark.Spark.post;

import com.blog.entity.User;
import com.blog.exception.InvalidUserData;
import com.blog.service.SignUpService;
import com.blog.util.JsonTransformer;

/**
 * @author Timur Berezhnoi
 */
public class SignUpController {

	private SignUpService userService;

	public SignUpController(SignUpService userService) {
		this.userService = userService;
		setupRoutes();
	}

	private void setupRoutes() {
		post("/signup", (request, response) -> {
			User user = JsonTransformer.fromJson(request.body(), User.class);
			userService.signUp(user);
			
			response.body("{\"message\": \"Succesfully signedUp\"}");
			response.type("application/json");
			response.status(201);
			return response.body();
		});
		before("/signup", (request, response) -> {
			if(request.body().isEmpty()) {				
				response.type("application/json");
				halt(400, "{\"message\": \"Body cannot be empty.\"}");
			}
		});
		
		// TODO: think about create ExceptionHandler class 
		exception(InvalidUserData.class, (e, request, response) -> {
		    response.status(400);
		    response.type("application/json");
		    response.body("{\"message\": \"" + e.getMessage() + "\"}");
		});
	}
}