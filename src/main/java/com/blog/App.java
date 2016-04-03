package com.blog;

import com.blog.config.BlogConfig;
import com.blog.controller.IndexController;
import com.blog.controller.LoginController;
import com.blog.controller.PostController;
import com.blog.controller.SignUpController;
import com.blog.dao.mongoimpl.MongoDAOFactory;
import com.blog.exception.InvalidUserDataException;
import com.blog.service.LoginService;
import com.blog.service.PostService;
import com.blog.service.SignUpService;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class App {

    static {
        ipAddress(BlogConfig.SERVER_HOST.getValue());
        port(Integer.parseInt(BlogConfig.SERVER_PORT.getValue()));

        staticFileLocation("/public");

        before((request, response) -> {
            response.type("application/json");
            System.out.println(request.requestMethod() + " " + request.pathInfo());
        });


        // TODO: Think about ErrorHandler
        exception(InvalidUserDataException.class, (e, request, response) -> {
            response.status(BAD_REQUEST.getCode());
            response.body("{\"message\": \"" + e.getMessage() + "\"}");
        });
    }

    public static void main(String[] args) {
        new IndexController();
        new SignUpController(new SignUpService());
        new LoginController(new LoginService());
        new PostController(new PostService(MongoDAOFactory.getPostDAOMongo()));
    }
}