package com.blog.controller;

import com.blog.service.PostService;
import com.google.gson.Gson;

import static spark.Spark.get;

/**
 * @author Timur Berezhnoi
 */
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
        setupRoutes();
    }

    private void setupRoutes() {
        get("/posts", (request, response) -> {
            int limit = Integer.parseInt(request.queryParams("limit"));
            int pageNumber = Integer.parseInt(request.queryParams("pageNumber"));
            return postService.getPostsByDateDescending(limit, pageNumber);
        }, new Gson()::toJson);
    }
}