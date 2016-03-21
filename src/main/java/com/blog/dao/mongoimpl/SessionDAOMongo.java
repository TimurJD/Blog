package com.blog.dao.mongoimpl;

import com.blog.dao.SessionDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author Timur Berezhnoi
 */
public class SessionDAOMongo implements SessionDAO {

    private final MongoCollection<Document> sessionCollection;

    public SessionDAOMongo(final MongoDatabase database) {
        sessionCollection = database.getCollection("sessions");
    }

    @Override
    public String createSession(String identifier) {
        byte randomBytes[] = new byte[32];
        new SecureRandom().nextBytes(randomBytes);

        final String sessionID = new BASE64Encoder().encode(randomBytes);

        sessionCollection.insertOne(new Document("userIdentifier", identifier)
                                    .append("sessionID", sessionID));
        return sessionID;
    }

    @Override
    public void destroySession(String sessionID) {
        sessionCollection.deleteOne(eq("sessionID", sessionID));
    }

    @Override
    public boolean isSessionAlive(String sessionID) {
        Document session = sessionCollection.find(eq("sessionID", sessionID)).first();
        return session != null;
    }
}