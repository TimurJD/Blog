package com.blog.dao.mongoimpl;

import com.blog.dao.PostDAO;
import com.blog.entity.Post;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Sorts.descending;

/**
 * @author Timur Berezhnoi
 */
public class PostDAOMongo implements PostDAO {

    private final MongoCollection<Document> postCollection;

    public PostDAOMongo(final MongoDatabase database) {
        this.postCollection = database.getCollection("posts");
    }

    @Override
    public void addPost(Post post) {
        postCollection.insertOne(new Document("title", post.getTitle())
                                .append("body", post.getBody())
                                .append("date", new Date()));
    }

    @Override
    public List<Post> getPostsByDateDescending(int limit, int pageNumber) {
        FindIterable<Document> iterable = postCollection.find().skip(limit * (pageNumber - 1)).limit(limit).sort(descending("date"));

        List<Post> result;

        if(iterable == null) {
            return null;
        } else {
            result = new ArrayList<>();

            for(Document document: iterable) {
                result.add(new Post(document.getString("title"), document.getString("body"), document.getDate("date")));
            }

            return result;
        }
    }
}