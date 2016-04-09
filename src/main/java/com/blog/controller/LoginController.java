package com.blog.controller;

import com.blog.service.LoginService;
import com.blog.service.SessionService;
import com.blog.util.JsonTransformer;
import com.google.gson.JsonObject;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.HttpStatus.OK;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static com.blog.constant.ResponseMessage.LOGGED_IN;
import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class LoginController {

    private final LoginService loginService;
    private final SessionService sessionService;

    public LoginController(LoginService loginService, SessionService sessionService) {
        this.loginService = loginService;
        this.sessionService = sessionService;
        setupRoutes();
    }

    private void setupRoutes() {
        post("/login", (requset, response) -> {
            JsonObject jsonObject = JsonTransformer.getJsonObject(requset.body());

            String email = jsonObject.get("email").getAsString();
            String password = jsonObject.get("password").getAsString();

            // TODO: think about remember me and cookie age
            response.body("{\"message\": \"" + LOGGED_IN.getMessage() + "\"}");
            response.cookie("session", sessionService.sessionBurn(email), 10000 * 60 * 60 * 24);
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