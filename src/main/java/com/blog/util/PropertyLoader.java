package com.blog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Timur Berezhnoi
 */
public class PropertyLoader {

	public static synchronized Properties loadProperties(String path) {
		Properties properties = new Properties();
		try(InputStream input = new FileInputStream(new File(path))) {
			properties.load(input);
		} catch(Exception e) {
			throw new RuntimeException("Error when loading configuration file", e);
		}
		return properties;
	}
}