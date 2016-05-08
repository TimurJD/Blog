package com.blog.service;

import com.blog.dao.PostDAO;
import com.blog.entity.Post;
import com.blog.exception.InvalidPostDataException;

import java.util.List;

import static com.blog.constant.ResponseMessage.INVALID_POST_TITLE;

/**
 * @author Timur Berezhnoi
 */
public class PostService {

    private final PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getPostsByDateDescending(int limit, int pageNumber) {
        return postDAO.getPostsByDateDescending(limit, pageNumber);
    }

    public void addNewPost(Post post) throws InvalidPostDataException {
        validatePost(post);

        post.setPermalink(generatePermalink(post.getTitle()));
        postDAO.addPost(post);
    }

    private void validatePost(Post post) throws InvalidPostDataException {
        if(post.getTitle() == null) {
            throw new InvalidPostDataException(INVALID_POST_TITLE.getMessage());
        }
    }

    private String generatePermalink(String link) {
        String permalink = link.replaceAll("\\s", "_");
        permalink = permalink.replaceAll("\\W", "");
        permalink = permalink.toLowerCase();
        return permalink;
    }
}