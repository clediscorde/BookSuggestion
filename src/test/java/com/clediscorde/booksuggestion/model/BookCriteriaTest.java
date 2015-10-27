package com.clediscorde.booksuggestion.model;

import com.clediscorde.booksuggestion.model.constant.Period;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class BookCriteriaTest {

    private BookCriteria bookCriteria;

    @Before
    public void setUp() throws Exception {
        bookCriteria = new BookCriteria();
    }

    @Test
    public void testGetAuthorDefault() throws Exception {
        assertNull(bookCriteria.getAuthor());
    }

    @Test
    public void testGetAuthor() throws Exception {
        bookCriteria.setAuthor("Author");
        assertEquals("Author", bookCriteria.getAuthor());
    }

    @Test
    public void testGetGenreDefault() throws Exception {
        assertNull(bookCriteria.getGenre());
    }

    @Test
    public void testGetGenre() throws Exception {
        bookCriteria.setGenre("Genre");
        assertEquals("Genre", bookCriteria.getGenre());
    }

    @Test
    public void testGetNumberOfPageMinDefault() throws Exception {
        assertNull(bookCriteria.getNumberOfPageMin());
    }

    @Test
    public void testGetNumberOfPageMin() throws Exception {
        bookCriteria.setNumberOfPageMin(100);
        assertEquals(100, bookCriteria.getNumberOfPageMin().intValue());
    }

    @Test
    public void testGetNumberOfPageMaxDefault() throws Exception {
        assertNull(bookCriteria.getNumberOfPageMax());
    }

    @Test
    public void testGetNumberOfPageMax() throws Exception {
        bookCriteria.setNumberOfPageMax(300);
        assertEquals(300, bookCriteria.getNumberOfPageMax().intValue());
    }

    @Test
    public void testGetPeriodDefault() throws Exception {
        assertNull(bookCriteria.getPeriod());
    }

    @Test
    public void testGetPeriod() throws Exception {
        bookCriteria.setPeriod(Period.CLASSIC);
        assertEquals(Period.CLASSIC, bookCriteria.getPeriod());
    }

    @Test
    public void testGetSortorderDefault() throws Exception {
        assertNull(bookCriteria.getSortOrder());
    }

    @Test
    public void testGetSortorder() throws Exception {
        bookCriteria.setSortOrder(new String[]{"SortOrder"});
        assertEquals(1, bookCriteria.getSortOrder().length);
        assertEquals("SortOrder", bookCriteria.getSortOrder()[0]);
    }

    @Test
    public void testGetSortorderRemoveEmptyValues() throws Exception {
        bookCriteria.setSortOrder(new String[]{"SortOrder", "", "SortOrder2"});
        assertEquals(2, bookCriteria.getSortOrder().length);
        assertEquals("SortOrder", bookCriteria.getSortOrder()[0]);
        assertEquals("SortOrder2", bookCriteria.getSortOrder()[1]);
    }
}