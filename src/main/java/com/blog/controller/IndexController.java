package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.IOUtils;

/**
 * @author Timur Berezhnoi
 */
public class IndexController implements Route {
    @Override
    public Object handle(Request request, Response response) {
        try(InputStream stream = getClass().getResourceAsStream("/public/index.html")) {
            response.type("text/html");
            return IOUtils.toString(stream);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}