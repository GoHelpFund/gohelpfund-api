package com.gohelpfund.api.v1.categories.controllers.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String id) {
        super("Could not find category " + id);
    }
}