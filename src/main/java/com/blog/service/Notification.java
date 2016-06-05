package com.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Timur Berezhnoi
 */
public class Notification {

    private final List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String getError() {
        return errors.stream().collect(Collectors.joining(", "));
    }
}