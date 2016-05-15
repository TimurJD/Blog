package com.blog.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author Timur Berezhnoi
 */
class JsonTransformer {
    static <T> T fromJson(String json, Class<T> entity) {
        return new Gson().fromJson(json, entity);
    }

    static JsonObject getJsonObject(String json) {
        return new JsonParser().parse(json).getAsJsonObject();
    }
}