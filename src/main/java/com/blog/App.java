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
	
	// TODO: Think about trace requests
	private static String requestInfoToString(Request request) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(request.requestMethod());
	    sb.append(" " + request.pathInfo());
	    return sb.toString();
	}
	
	static {
		ipAddress(BlogConstants.SERVER_HOST.getValue());
		port(Integer.parseInt(BlogConstants.SERVER_PORT.getValue()));
		
		staticFileLocation("/public");
        
        before((request, response) -> {
            System.out.println(request.requestMethod() + " " + request.pathInfo());
	    });

        after((request, response) -> response.header("cache-control", "no-cache"));
	}
	
	public static void main(String[] args) {
		new IndexController();
		new SignUpController(new SignUpService());
	}
}