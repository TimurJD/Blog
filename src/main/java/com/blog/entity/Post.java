package com.blog.entity;

import java.util.Date;

/**
 * @author Timur Berezhnoi
 */
public class Post {

    private String id;
    private String title;
    private String body;
    private Date dateOfCreation;
    private String permalink;

    public Post(String title, String body, Date dateOfCreation) {
        this.title = title;
        this.body = body;
        this.dateOfCreation = dateOfCreation;
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

    /**
     * @param dateOfCreation the dateOfCreation to set
     */
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    /**
     * @return the dateOfCreation
     */
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * @param permalink the permalink to set
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * @return the permalink
     */
    public String getPermalink() {
        return permalink;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + body + " " + dateOfCreation;
    }

}