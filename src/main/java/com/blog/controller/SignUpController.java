package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.SignUpService;
import com.blog.util.JsonTransformer;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.HttpStatus.CREATED;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static com.blog.constant.ResponseMessage.SIGNED_UP;
import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class SignUpController {

	private final SignUpService userService;

	public SignUpController(SignUpService userService) {
		this.userService = userService;
		setupRoutes();
	}

	private void setupRoutes() {
		post("/signup", (request, response) -> {
			User user = JsonTransformer.fromJson(request.body(), User.class);
			userService.signUp(user);

			response.body("{\"message\": \"" + SIGNED_UP.getMessage() + "\"}");
			response.status(CREATED.getCode());
			return response.body();
		});

		before("/signup", (request, response) -> {
			if(request.body().isEmpty()) {
				halt(BAD_REQUEST.getCode(), "{\"message\": \"" + EMPTY_BODY.getMessage() + "\"}");
			}
		});
	}
}