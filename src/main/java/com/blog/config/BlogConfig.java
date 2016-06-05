package com.blog.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Timur Berezhnoi
 */
public enum BlogConfig {

	// TODO: Think about split this constants to 2 different enums???
	DB_HOST("mongodb.host"),
	DB_PORT("mongodb.port"),
	DB_NAME("mongodb.db"),
	
	SERVER_HOST("server.host"),
	SERVER_PORT("server.port");

    private static Properties properties;

    private static class PropertyLoader {
        static synchronized Properties loadProperties(String path) {
            Properties properties = new Properties();
            try(InputStream input = new FileInputStream(new File(path))) {
                properties.load(input);
            } catch(Exception e) {
                throw new RuntimeException("Error when loading configuration file", e);
            }
            return properties;
        }
    }

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