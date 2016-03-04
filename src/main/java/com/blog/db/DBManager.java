package com.blog.db;

import com.blog.config.BlogConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author Timur Berezhnoi
 */
public enum DBManager {

	INSTANCE;
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	
    DBManager() {
		mongoClient = new MongoClient(BlogConstants.DBHOST.getValue(), Integer.parseInt(BlogConstants.DBPORT.getValue()));
		mongoDatabase = mongoClient.getDatabase(BlogConstants.DBName.getValue());
	}
	
	public MongoDatabase getDataBase() {
		return mongoDatabase;
	}

	public void close() {
		mongoClient.close();
	}
}