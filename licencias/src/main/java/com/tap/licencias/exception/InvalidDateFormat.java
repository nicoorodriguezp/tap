package com.tap.licencias.exception;

public class InvalidDateFormat extends Exception{
    public InvalidDateFormat() {
    }

    public InvalidDateFormat(String message) {
        super(message);
    }
}
