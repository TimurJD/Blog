package com.blog.util;

import com.google.gson.Gson;

/**
 * @author Timur Berezhnoi
 */
public class JsonTransformer {
    public static <T> T fromJson(String json, Class<T> entity) {
        return new Gson().fromJson(json, entity);
    }	
}