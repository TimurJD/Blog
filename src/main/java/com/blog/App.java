package com.blog;

import com.blog.config.BlogConfig;
import com.blog.controller.IndexController;
import com.blog.controller.LoginController;
import com.blog.controller.SignUpController;
import com.blog.dao.mongoimpl.MongoDAOFactory;
import com.blog.service.LoginService;
import com.blog.service.SessionService;
import com.blog.service.SignUpService;

import static spark.Spark.*;

//import com.blog.service.PostService;

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
    }

    public static void main(String[] args) throws InterruptedException {
        new IndexController();
        new SignUpController(new SignUpService(MongoDAOFactory.getUserDAOMongo()));
        new LoginController(new LoginService(MongoDAOFactory.getUserDAOMongo()),
                            new SessionService(MongoDAOFactory.getSessionDAOMongo()));
//        new PostController(new PostService(MongoDAOFactory.getPostDAOMongo()));
    }
}