package com.clediscorde.booksuggestion.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class BookSuggestionInitializerTest {

    private BookSuggestionInitializer bookSuggestionInitializer;

    @Before
    public void setUp() throws Exception {
        bookSuggestionInitializer = new BookSuggestionInitializer();
    }

    @Test
    public void testGetServletMappings() throws Exception {
        assertEquals(1, bookSuggestionInitializer.getServletMappings().length);
        assertEquals("/", bookSuggestionInitializer.getServletMappings()[0]);
    }

    @Test
    public void testGetRootConfigClasses() throws Exception {
        assertEquals(1, bookSuggestionInitializer.getRootConfigClasses().length);
        assertEquals(BookSuggestionAppConfig.class, bookSuggestionInitializer.getRootConfigClasses()[0]);
    }

    @Test
    public void testGetServletConfigClasses() throws Exception {
        assertEquals(1, bookSuggestionInitializer.getServletConfigClasses().length);
        assertEquals(BookSuggestionAppConfig.class, bookSuggestionInitializer.getServletConfigClasses()[0]);
    }
}