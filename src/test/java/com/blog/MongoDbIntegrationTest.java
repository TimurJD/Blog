package com.blog;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;

/**
 * @author Timur Berezhnoi
 */
@Ignore
public class MongoDbIntegrationTest {

	private static final String BOOK_COLLECTION_NAME = "bookCollection";
 
	private MongodForTestsFactory factory;
	private MongoClient mongo;
 
	@Before
	public void setup() throws Exception {
		factory = MongodForTestsFactory.with(Version.Main.PRODUCTION);
		mongo = factory.newMongo();
	}
 
	@After
	public void teardown() throws Exception {
		if (factory != null)
			factory.shutdown();
	}
 
	@Test
	public void shouldCreateAndGetCollection() {
		MongoDatabase db = mongo.getDatabase("test-" + UUID.randomUUID());
		db.createCollection(BOOK_COLLECTION_NAME);
		
		MongoCollection<BasicDBObject> collection = db.getCollection(BOOK_COLLECTION_NAME, BasicDBObject.class);
	
		assertNotNull(collection);
	}
}