package com.blog.dao;

import com.blog.entity.Post;
import com.blog.entity.User;

import java.util.List;

/**
 * @author Timur Berezhnoi
 */
public interface PostDAO {
    boolean addPost(String title, String body, User username);
    List<Post> getAll();
}