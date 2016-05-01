package com.blog;

import com.blog.db.DBManager;
import com.mongodb.client.MongoDatabase;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


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
}