package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.LoginService;
import com.blog.service.Notification;
import com.blog.service.SessionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.HttpStatus.OK;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static com.blog.constant.ResponseMessage.LOGIN_FAIL;
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
            Map<String, Object> responseData = new HashMap<>();
            Map requestBody = new Gson().fromJson(requset.body(), Map.class);

            String email = (String) requestBody.get("email");
            String password = (String) requestBody.get("password");

            Notification notification = loginService.validateLoginData(email, password);

            if(notification.hasErrors()) {
                response.status(BAD_REQUEST.getCode());
                responseData.put("error", notification.getError());
                return responseData;
            }

            User user = loginService.getUser(email, password);

            if(user == null) {
                notification.addError(LOGIN_FAIL.getMessage());
                response.status(BAD_REQUEST.getCode());
                responseData.put("error", notification.getError());
                return responseData;
            }

            responseData.put("user", user);
            setSession(email, response);

            return responseData;

        }, new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()::toJson);

        before("/login", (request, response) -> {
            if(request.body().isEmpty()) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("error", EMPTY_BODY.getMessage());
                halt(BAD_REQUEST.getCode(), new Gson().toJson(responseData));
            }
        });
    }

    private void setSession(String email, Response response) {
        response.cookie("session", sessionService.sessionBurn(email), 10000 * 60 * 60 * 24);
        response.status(OK.getCode());
    }
}