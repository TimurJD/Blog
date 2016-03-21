package com.blog.controller;

import com.blog.service.LoginService;
import com.blog.util.JsonTransformer;
import com.google.gson.*;

import static com.blog.constant.HttpStatus.OK;
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

            response.cookie("session", sessionService.login(email, password), 3600, true);
            response.body("{\"message\": \"" + LOGGED_IN.getMessage() + "\"}");
            response.status(OK.getCode());
            return response.body();
        });
    }
}