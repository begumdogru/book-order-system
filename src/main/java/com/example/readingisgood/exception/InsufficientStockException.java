package com.example.readingisgood.exception;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException() {
        super("Insufficient stock for this product.");
    }

    public InsufficientStockException(String message) {
        super(message);
    }
}
