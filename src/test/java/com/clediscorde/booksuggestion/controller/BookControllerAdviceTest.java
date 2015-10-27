package com.clediscorde.booksuggestion.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by clediscorde on 2015-10-24.
 */
public class BookControllerAdviceTest {

    private BookControllerAdvice controllerAdvice;

    @Before
    public void setUp() throws Exception {
        controllerAdvice = new BookControllerAdvice();
    }

    @Test
    public void testException() throws Exception {
        Exception exception = mock(Exception.class);
        when(exception.getMessage()).thenReturn("Message");
        assertEquals("Message", controllerAdvice.exception(exception));
    }
}