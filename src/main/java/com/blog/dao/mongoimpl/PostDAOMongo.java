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
                                .append("author", post.getAuthor().getId())
                                .append("date", new Date()));
    }

    @Override
    public List<Post> getAllPosts() {
        FindIterable<Document> iterable = postCollection.find();
        List<Post> result = new ArrayList<>();

        for(Document document: iterable) {
            result.add(new Post(document.getString("title"), document.getString("body"),
                    /*(User) document.get("author"),*/ document.getDate("date")));
        }

        return result;
    }
}