package com.clediscorde.booksuggestion.exception;

/**
 * Created by clediscorde on 2015-10-22.
 * Exception when trying to save a book cause by invalid attributes.
 */
public class InvalidBookException extends Throwable {
    public InvalidBookException(String s) {
        super(s);
    }
}
