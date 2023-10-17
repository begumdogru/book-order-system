package com.example.readingisgood.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Book not found!");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}

