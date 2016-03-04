package com.blog.controller;

import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.halt;
import static spark.Spark.post;

import static com.blog.constant.HttpStatus.CREATED;
import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.ResponseMessage.SIGNED_UP;

import com.blog.entity.User;
import com.blog.exception.InvalidUserDataException;
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

			response.body("{\"message\": \"" + SIGNED_UP.getMessage() + "\"}");
			response.status(CREATED.getCode());
			return response.body();
		});

		before("/signup", (request, response) -> {
			if(request.body().isEmpty()) {
				halt(BAD_REQUEST.getCode(), "{\"message\": \"" + EMPTY_BODY.getMessage() + "\"}");
			}
		});

		// TODO: think about create ExceptionHandler class
		exception(InvalidUserDataException.class, (e, request, response) -> {
            response.status(BAD_REQUEST.getCode());
            response.body("{\"message\": \"" + e.getMessage() + "\"}");
		});
	}
}