package com.blog.config;

import java.util.Properties;

import com.blog.util.PropertyLoader;

/**
 * @author Timur Berezhnoi
 */
public enum BlogConfig {

	// TODO: Think about split this constants to 2 different enums???
	DBHOST("mongodb.host"),
	DBPORT("mongodb.port"),
	DBName("mongodb.db"),
	
	SERVER_HOST("server.host"),
	SERVER_PORT("server.port");
	
	private static Properties properties;
	static {
		properties = PropertyLoader.loadProperties("src/main/resources/app.properties");
	}
	
	private final String key;
	
	BlogConfig(String key) {
		this.key = key;
	}

	/**
	 * @return propertie or null if value doesn't exists
	 */
	public String getValue() {
		return properties.getProperty(key);
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}