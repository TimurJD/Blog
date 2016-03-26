package com.blog.dao.mongoimpl;

import com.blog.db.DBManager;

/**
 * @author Timur Berezhnoi
 */
public final class MongoDAOFactory {

    private static SessionDAOMongo sessionDAOMongo;
    private static UserDAOMongo userDAOMongo;

    private MongoDAOFactory() {
        throw new UnsupportedOperationException("Do not create an instance of the singleton");
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
}