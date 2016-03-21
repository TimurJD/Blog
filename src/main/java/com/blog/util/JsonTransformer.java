package com.blog.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author Timur Berezhnoi
 */
public class JsonTransformer {
    public static <T> T fromJson(String json, Class<T> entity) {
        return new Gson().fromJson(json, entity);
    }

    public static JsonObject getJsonObject(String json) {
        return new JsonParser().parse(json).getAsJsonObject();
    }
}