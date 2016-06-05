package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.Notification;
import com.blog.service.SignUpService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.HttpStatus.CREATED;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class SignUpController {

	private final SignUpService signupService;

	public SignUpController(SignUpService signupService) {
		this.signupService = signupService;
		setupRoutes();
	}

	private void setupRoutes() {
		post("/signup", (request, response) -> {
            Map<String, Object> responseData = new HashMap<>();
			User user = JsonTransformer.fromJson(request.body(), User.class);

			Notification notification = signupService.validate(user);

            if(notification.hasErrors()) {
                response.status(BAD_REQUEST.getCode());
                responseData.put("error", notification.getError());
                return responseData;
            }

            user.setEmail(user.getEmail().toLowerCase());
            signupService.signUp(user);
			response.status(CREATED.getCode());

			return responseData;
		}, new Gson()::toJson);

		before("/signup", (request, response) -> {
			if(request.body().isEmpty()) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("error",  EMPTY_BODY.getMessage());
				halt(BAD_REQUEST.getCode(), new Gson().toJson(responseData));
			}
		});
	}
}