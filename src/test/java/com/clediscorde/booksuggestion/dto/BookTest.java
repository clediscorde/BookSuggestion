package com.clediscorde.booksuggestion.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clediscorde on 2015-10-20.
 */
public class BookTest {

    private Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book("Author", "Genre", 100, 2015, 3);
    }

    @Test
    public void testGetAuthor() throws Exception {
        assertEquals("Author", book.getAuthor());
    }

    @Test
    public void testGetGenre() throws Exception {
        assertEquals("Genre", book.getGenre());
    }

    @Test
    public void testGetNumberOfPage() throws Exception {
        assertEquals(100, book.getNumberOfPage());
    }

    @Test
    public void testGetYearOfPublication() throws Exception {
        assertEquals(2015, book.getYearOfPublication());
    }

    @Test
    public void testGetOverallReatin() throws Exception {
        assertEquals(3, book.getOverallRating());
    }
}