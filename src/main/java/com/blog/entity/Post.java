package com.blog.entity;

/**
 * @author Timur Berezhnoi
 */
public class Post {

    private String id;
    private String title;
    private String body;
    private final User author;

    public Post(String title, String body, User author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param body the title to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + body;
    }
}