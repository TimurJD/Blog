package com.blog.controller;

import com.blog.service.LoginService;
import com.blog.util.JsonTransformer;
import com.google.gson.*;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.HttpStatus.OK;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static spark.Spark.*;

import static com.blog.constant.ResponseMessage.LOGGED_IN;

/**
 * @author Timur Berezhnoi
 */
public class LoginController {

    private final LoginService sessionService;

    public LoginController(LoginService sessionService) {
        this.sessionService = sessionService;
        setupRoutes();
    }

    private void setupRoutes() {
        post("/login", (requset, response) -> {
            JsonObject jsonObject = JsonTransformer.getJsonObject(requset.body());

            String email = jsonObject.get("email").getAsString();
            String password = jsonObject.get("password").getAsString();

            // TODO: think about remember me and cookie age
            response.cookie("session", sessionService.login(email, password), 10000 * 60 * 60 * 24, true);
            response.body("{\"message\": \"" + LOGGED_IN.getMessage() + "\"}");
            response.status(OK.getCode());
            return response.body();
        });

        before("/login", (request, response) -> {
            if(request.body().isEmpty()) {
                halt(BAD_REQUEST.getCode(), "{\"message\": \"" + EMPTY_BODY.getMessage() + "\"}");
            }
        });
    }
}
