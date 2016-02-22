package com.blog.dao.mongoimol;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author Timur Berezhnoi
 */
public class UserDAOMongo implements UserDAO {
	
	private final MongoCollection<Document> usersCollection;
	
	public UserDAOMongo(final MongoDatabase database) {
		usersCollection = database.getCollection("users");
	}
	
	@Override
	public boolean addUser(User user) {
		Document userDocument = new Document()
							.append("email", user.getEmail())
							.append("name", user.getName())
							.append("password", user.getPassword());
		try {
			usersCollection.insertOne(userDocument);
			return true;
		} catch (MongoWriteException e) {
			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Username already in use: " + user.getName());
                return false;
            }
			throw e;
		}
	}

	@Override
	public User getUserByEmail(String email) {
			Document document = usersCollection.find(eq("email", email)).first();
			if(document == null) {
				return null;				
			}
			return new User(document.get("email").toString(), document.get("name").toString(), document.get("password").toString());
	}
}