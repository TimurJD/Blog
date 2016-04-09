package com.blog.service;

import com.blog.dao.SessionDAO;

/**
 * @author Timur Berezhnoi
 */
public class SessionService {

    private final SessionDAO sessionDAO;

    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    public String sessionBurn(String email) {
        return sessionDAO.createSession(email.toLowerCase());
    }
}