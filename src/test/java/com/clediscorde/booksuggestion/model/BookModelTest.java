package com.clediscorde.booksuggestion.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class BookModelTest {

    private BookModel bookModel;
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        bookModel = new BookModel();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetIdDefault() throws Exception {
        assertNull(bookModel.getId());
    }

    @Test
    public void testGetId() throws Exception {
        setBookModelValues();
        assertEquals("Id", bookModel.getId());
    }

    @Test
    public void testGetTitleDefault() throws Exception {
        assertNull(bookModel.getTitle());
    }

    @Test
    public void testGetTitle() throws Exception {
        setBookModelValues();
        assertEquals("Title", bookModel.getTitle());
    }

    @Test
    public void testGetTitleConstraintNotNull() throws Exception {
        setBookModelValues();
        bookModel.setTitle(null);
        validateError("may not be empty");
    }

    @Test
    public void testGetTitleConstraintNotEmpty() throws Exception {
        setBookModelValues();
        bookModel.setTitle("");
        validateError("may not be empty");
    }

    @Test
    public void testGetAuthorDefault() throws Exception {
        assertNull(bookModel.getAuthor());
    }

    @Test
    public void testGetAuthor() throws Exception {
        setBookModelValues();
        assertEquals("Author", bookModel.getAuthor());
    }

    @Test
    public void testGetAuthorConstraintNotNull() throws Exception {
        setBookModelValues();
        bookModel.setAuthor(null);
        validateError("may not be empty");
    }

    @Test
    public void testGetAuthorConstraintNotEmpty() throws Exception {
        setBookModelValues();
        bookModel.setAuthor("");
        validateError("may not be empty");
    }

    @Test
    public void testGetGenreDefault() throws Exception {
        assertNull(bookModel.getGenre());
    }

    @Test
    public void testGetGenre() throws Exception {
        setBookModelValues();
        assertEquals("Genre", bookModel.getGenre());
    }

    @Test
    public void testGetGenreConstraintNotNull() throws Exception {
        setBookModelValues();
        bookModel.setGenre(null);
        validateError("may not be empty");
    }

    @Test
    public void testGetGenreConstraintNotEmpty() throws Exception {
        setBookModelValues();
        bookModel.setGenre("");
        validateError("may not be empty");
    }

    @Test
    public void testGetNumberOfPageDefault() throws Exception {
        assertEquals(0, bookModel.getNumberOfPage());
    }

    @Test
    public void testGetNumberOfPage() throws Exception {
        setBookModelValues();
        assertEquals(100, bookModel.getNumberOfPage());
    }

    @Test
    public void testGetYearOfPublicationDefault() throws Exception {
        assertEquals(0, bookModel.getYearOfPublication());
    }

    @Test
    public void testGetNumberOfPage0() throws Exception {
        setBookModelValues();
        bookModel.setNumberOfPage(0);
        validateError("must be greater than or equal to 1");
    }

    @Test
    public void testGetYearOfPublication() throws Exception {
        setBookModelValues();
        assertEquals(1990, bookModel.getYearOfPublication());
    }

    @Test
    public void testGetOverallRatingDefault() throws Exception {
        assertEquals(0, bookModel.getOverallRating());
    }

    @Test
    public void testGetOverallRating() throws Exception {
        setBookModelValues();
        assertEquals(5, bookModel.getOverallRating());
    }

    @Test
    public void testGetNumberOfPageNegative() throws Exception {
        setBookModelValues();
        bookModel.setOverallRating(-1);
        validateError("must be greater than or equal to 0");
    }

    @Test
    public void testGetNumberOfPage6() throws Exception {
        setBookModelValues();
        bookModel.setOverallRating(6);
        validateError("must be less than or equal to 5");
    }

    private void setBookModelValues() {
        bookModel.setId("Id");
        bookModel.setTitle("Title");
        bookModel.setAuthor("Author");
        bookModel.setGenre("Genre");
        bookModel.setNumberOfPage(100);
        bookModel.setYearOfPublication(1990);
        bookModel.setOverallRating(5);
    }

    private void validateError(String message) {
        Set<ConstraintViolation<BookModel>> constraintViolations = validator.validate(bookModel);

        assertEquals(1, constraintViolations.size());
        assertEquals(message, constraintViolations.iterator().next().getMessage());
    }
}