package com.blog;

import spark.Request;

import com.blog.config.BlogConstants;
import com.blog.controller.IndexController;
import com.blog.controller.SignUpController;
import com.blog.service.SignUpService;

import static spark.Spark.*;

/**
 * @author Timur Berezhnoi
 */
public class App {

	static {
		ipAddress(BlogConstants.SERVER_HOST.getValue());
		port(Integer.parseInt(BlogConstants.SERVER_PORT.getValue()));
		
		staticFileLocation("/public");

        before((request, response) -> {
            response.type("application/json");
            System.out.println(request.requestMethod() + " " + request.pathInfo());
	    });
	}
	
	public static void main(String[] args) {
		new IndexController();
		new SignUpController(new SignUpService());
	}
}