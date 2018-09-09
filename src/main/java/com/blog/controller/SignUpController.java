package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.Notification;
import com.blog.service.SignUpService;
import com.google.gson.Gson;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.HttpStatus.CREATED;
import static com.blog.constant.ResponseMessage.EMAIL_IN_USE;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class SignUpController implements Route {

	private final SignUpService signupService;

	public SignUpController(SignUpService signupService) {
		this.signupService = signupService;
	}

	@Override
	public Object handle(Request request, Response response) {
		Map<String, Object> responseData = new HashMap<>();
		User user = JsonTransformer.fromJson(request.body(), User.class);

		Notification notification = signupService.validate(user);

		user.setEmail(user.getEmail().toLowerCase());

		if(signupService.isUserExists(user.getEmail())) {
			notification.addError(EMAIL_IN_USE.getMessage());
			response.status(BAD_REQUEST.getCode());
			responseData.put("message", notification.getError());
			return responseData;
		}

		signupService.signUp(user);
		response.status(CREATED.getCode());

		return responseData;
	}

	public static final class SignupFilter implements Filter {

		@Override
		public void handle(Request request, Response response) {
			if(request.body().isEmpty()) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("message",  EMPTY_BODY.getMessage());
				halt(BAD_REQUEST.getCode(), new Gson().toJson(responseData));
			}
		}
	}
}