package com.blog.db;

import com.blog.config.DBConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author Timur Berezhnoi
 */
public enum DBManager {

	INSTANCE;
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	
	private DBManager() {
		mongoClient = new MongoClient(DBConstant.HOST.getValue(), Integer.parseInt(DBConstant.PORT.getValue()));
		mongoDatabase = mongoClient.getDatabase(DBConstant.DBName.getValue());
	}
	
	public MongoDatabase getDataBase() {
		return mongoDatabase;
	}

	public void close() {
		mongoClient.close();
	}
}