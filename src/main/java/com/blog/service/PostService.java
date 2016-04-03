package com.blog.service;

import com.blog.dao.PostDAO;
import com.blog.entity.Post;

import java.util.List;

/**
 * @author Timur Berezhnoi
 */
public class PostService {

    private final PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }
}