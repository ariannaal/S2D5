package com.example.S2D5.exceptions;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(String message) {
        super(message);
    }

    public NotFoundEx(int id) {
        super(id + " non trovato!");
    }
}
