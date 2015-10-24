package com.clediscorde.booksuggestion.exception;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class InvalidBookExceptionTest {
    @Test
    public void testException() throws Exception {
        InvalidBookException exception = new InvalidBookException("junit");
        assertEquals("junit", exception.getMessage());
    }
}