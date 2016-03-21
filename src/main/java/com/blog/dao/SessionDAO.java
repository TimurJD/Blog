package com.blog.dao;

/**
 * @author Timur Berezhnoi
 */
public interface SessionDAO {
    String createSession(String identifier);
    void destroySession(String sessionID);
    boolean isSessionAlive(String sessionID);

}