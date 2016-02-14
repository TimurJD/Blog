package com.blog;

import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.blog.db.DBManager;
import com.mongodb.client.MongoDatabase;


/**
 * @author Timur Berezhnoi
 */
public class DBManagerTest {

	private static MongoDatabase testDb;
	
	@BeforeClass
	public static void setUp() {
		 testDb = DBManager.INSTANCE.getDataBase();
	}
	
	@Test
	public void dbShouldntBeNull() {
		assertNotNull(testDb);
	}
	
	@Test
	public void dbShouldReturnCollection() {
		assertNotNull(testDb.getCollection("users"));
	}
	
	@AfterClass
	public static void tearDown() {
		DBManager.INSTANCE.close();
	}
}