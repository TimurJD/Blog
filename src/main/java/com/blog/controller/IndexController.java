package com.blog.controller;

import static spark.Spark.get;

import java.io.IOException;
import java.io.InputStream;

import spark.utils.IOUtils;

/**
 * @author Timur Berezhnoi
 */
public class IndexController {
	
	public IndexController() {
		setupRoutes();
	}
	
	private void setupRoutes() {
		get("/", (request, resonse) -> {
			try(InputStream stream = getClass().getResourceAsStream("/public/index.html")) {
	            return IOUtils.toString(stream);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
		});
	}
}