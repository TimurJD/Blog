package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.LoginService;
import com.blog.service.Notification;
import com.blog.service.SessionService;
import com.google.gson.Gson;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

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
public class LoginController implements Route {

    private final LoginService loginService;
    private final SessionService sessionService;

    public LoginController(LoginService loginService, SessionService sessionService) {
        this.loginService = loginService;
        this.sessionService = sessionService;
    }

    /**
     * Invoked when a request is made on this route's corresponding path e.g. '/hello'
     *
     * @param request  The request object providing information about the HTTP request
     * @param response The response object providing functionality for modifying the response
     * @return The content to be set in the response
     */
    @Override
    public Object handle(Request request, Response response) {
        Map<String, Object> responseData = new HashMap<>();
        Map requestBody = new Gson().fromJson(request.body(), Map.class);

        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");

        Notification notification = loginService.validateLoginData(email, password);

        if(notification.hasErrors()) {
            response.status(BAD_REQUEST.getCode());
            responseData.put("message", notification.getError());
            return responseData;
        }

        User user = loginService.getUser(email, password);

        if(user == null) {
            notification.addError(LOGIN_FAIL.getMessage());
            response.status(BAD_REQUEST.getCode());
            responseData.put("message", notification.getError());
            return responseData;
        }

        responseData.put("user", user);

        setSession(email, response);

        return responseData;
    }

    private void setSession(String email, Response response) {
        response.cookie("session", sessionService.sessionBurn(email), 10000 * 60 * 60 * 24);
        response.status(OK.getCode());
    }

    public static final class LoginFilter implements Filter {

        /**
         * Invoked when a request is made on this filter's corresponding path e.g. '/hello'
         *
         * @param request  The request object providing information about the HTTP request
         * @param response The response object providing functionality for modifying the response
         */
        @Override
        public void handle(Request request, Response response) {
            if(request.body().isEmpty()) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("message", EMPTY_BODY.getMessage());
                halt(BAD_REQUEST.getCode(), new Gson().toJson(responseData));
            }
        }
    }
}