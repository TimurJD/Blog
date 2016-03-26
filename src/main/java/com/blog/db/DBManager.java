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
		mongoClient = new MongoClient(BlogConfig.DBHOST.getValue(), Integer.parseInt(BlogConfig.DBPORT.getValue()));
		mongoDatabase = mongoClient.getDatabase(BlogConfig.DBName.getValue());
	}
	
	public MongoDatabase getDataBase() {
		return mongoDatabase;
	}

	public void close() {
		mongoClient.close();
	}
}