package com.blog.dao;

import com.blog.entity.Post;

import java.util.List;

/**
 * @author Timur Berezhnoi
 */
public interface PostDAO {
    void addPost(Post post);
    List<Post> getPostsByDateDescending(int limit, int pageNumber);
}