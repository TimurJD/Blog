package com.blog.db;

import com.blog.config.BlogConfig;
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
		mongoClient = new MongoClient(BlogConfig.DB_HOST.getValue(), Integer.parseInt(BlogConfig.DB_PORT.getValue()));
		mongoDatabase = mongoClient.getDatabase(BlogConfig.DB_NAME.getValue());
	}
	
	public MongoDatabase getDataBase() {
		return mongoDatabase;
	}

	public void close() {
		mongoClient.close();
	}
}