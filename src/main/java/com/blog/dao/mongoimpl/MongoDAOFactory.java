package com.blog.dao.mongoimpl;

import com.blog.dao.PostDAO;
import com.blog.db.DBManager;

/**
 * @author Timur Berezhnoi
 */
public final class MongoDAOFactory {

    private static SessionDAOMongo sessionDAOMongo;
    private static UserDAOMongo userDAOMongo;
    private static PostDAOMongo postDAOMongo;

    private MongoDAOFactory() {
        throw new UnsupportedOperationException("Do not create an instance of the class");
    }

    public static SessionDAOMongo getSessionDAOMongo() {
        if(sessionDAOMongo == null) {
            sessionDAOMongo = new SessionDAOMongo(DBManager.INSTANCE.getDataBase());
        }
        return sessionDAOMongo;
    }

    public static UserDAOMongo getUserDAOMongo() {
        if(userDAOMongo == null) {
            userDAOMongo = new UserDAOMongo(DBManager.INSTANCE.getDataBase());
        }
        return userDAOMongo;
    }

    public static PostDAO getPostDAOMongo() {
        if(postDAOMongo == null) {
            postDAOMongo = new PostDAOMongo(DBManager.INSTANCE.getDataBase());
        }
        return postDAOMongo;
    }
}