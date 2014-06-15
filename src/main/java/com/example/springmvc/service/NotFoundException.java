package com.example.springmvc.service;

/**
 * Created by tatsuya on 2014/05/31.
 */
public class NotFoundException extends Exception {
    public NotFoundException(final String message) {
        super(message);
    }
}
