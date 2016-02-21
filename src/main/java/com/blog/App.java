package com.blog;

import static spark.Spark.before;
import static spark.Spark.ipAddress;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import spark.Request;

import com.blog.config.BlogConstants;
import com.blog.controller.IndexController;
import com.blog.controller.SignUpController;
import com.blog.service.SignUpService;

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
	}
	
	public static void main(String[] args) {
		new IndexController();
		new SignUpController(new SignUpService());
	}
}