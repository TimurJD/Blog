package com.blog.controller;

import com.blog.entity.Post;
import com.blog.service.PostService;
import com.google.gson.Gson;

import static com.blog.constant.HttpStatus.BAD_REQUEST;
import static com.blog.constant.ResponseMessage.EMPTY_BODY;
import static spark.Spark.*;

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

        post("/newPost", (request, response) -> {
            Post post = JsonTransformer.fromJson(request.body(), Post.class);
            postService.addNewPost(post);
            return post;
        }, new Gson()::toJson);

        before("/newPost", (request, response) -> {
            if(request.body().isEmpty()) {
                halt(BAD_REQUEST.getCode(), "{\"message\": \"" + EMPTY_BODY.getMessage() + "\"}");
            }
        });
    }
}