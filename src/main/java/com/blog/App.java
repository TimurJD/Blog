package com.blog;

import com.blog.config.BlogConfig;
import com.blog.controller.IndexController;
import com.blog.controller.LoginController;
import com.blog.controller.SignUpController;
import com.blog.dao.mongoimpl.MongoDAOFactory;
import com.blog.service.LoginService;
import com.blog.service.SessionService;
import com.blog.service.SignUpService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class App {

    private static final String INDEX_PATH = "/";
    private static final String SIGN_UP_PATH = "/signup";
    private static final String LOGIN_PATH = "/login";

    static {
        ipAddress(BlogConfig.SERVER_HOST.getValue());
        port(Integer.parseInt(BlogConfig.SERVER_PORT.getValue()));

        staticFileLocation("/public");

        before((request, response) -> {
            response.type("application/json");
            System.out.println(request.requestMethod() + " " + request.pathInfo());
        });
    }

    public static void main(String[] args) {
//        new PostController(new PostService(MongoDAOFactory.getPostDAOMongo()));

        initRoutes();
        initFilters();
    }

    private static void initRoutes() {
        get(INDEX_PATH, new IndexController());
        post(SIGN_UP_PATH, new SignUpController(new SignUpService(MongoDAOFactory.getUserDAOMongo())), new Gson()::toJson);
        post(LOGIN_PATH, new LoginController(new LoginService(MongoDAOFactory.getUserDAOMongo()),
                                             new SessionService(MongoDAOFactory.getSessionDAOMongo())),
                                             new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()::toJson);
    }

    private static void initFilters() {
        before(SIGN_UP_PATH, new SignUpController.SignupFilter());
        before(LOGIN_PATH, new LoginController.LoginFilter());
    }
}