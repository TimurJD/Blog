package com.blog.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Timur Berezhnoi
 */
public enum DBConstant {

	HOST("mongodb.host"),
	PORT("mongodb.port"),
	DBName("mongodb.db"),
	SOME("zzz");
	
	private static Properties properties;
	static {
		properties = new Properties();
		try(InputStream input = new FileInputStream(new File("src/main/resources/db.properties"))) {
			properties.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Error when loading configuration file", e);
		}
	}
	
	private final String key;
	
	private DBConstant(String key) {
		this.key = key;
	}

	/**
	 * @return propertie or null if value doesn't exists
	 */
	public String getValue() {
		String value = properties.getProperty(key);
		if(properties.getProperty(key) == null) {
			
		}
		return value; 
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public static void main(String[] args) {
		System.out.println(SOME.getValue());
	}
}