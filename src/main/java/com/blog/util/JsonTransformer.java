package com.blog.util;

import com.google.gson.Gson;

/**
 * @author Timur Berezhnoi
 */
public class JsonTransformer {
    public static <T extends Object> T fromJson(String json, Class<T> classe) {
        return new Gson().fromJson(json, classe);
    }	
}