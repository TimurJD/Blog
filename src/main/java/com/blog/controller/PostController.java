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
        get("/posts", (request, response) -> postService.getAllPosts(), new Gson()::toJson);
    }
}