package com.blog.controller;

import com.google.gson.Gson;

/**
 * @author Timur Berezhnoi
 */
class JsonTransformer {
    static <T> T fromJson(String json, Class<T> entity) {
        return new Gson().fromJson(json, entity);
    }
}