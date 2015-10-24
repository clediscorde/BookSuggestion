package com.clediscorde.booksuggestion.service;

import com.clediscorde.booksuggestion.dto.Book;
import com.clediscorde.booksuggestion.model.BookCriteria;
import com.clediscorde.booksuggestion.model.BookModel;
import com.clediscorde.booksuggestion.model.constant.Period;
import com.clediscorde.booksuggestion.repository.BookRepository;
import com.mongodb.DBCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by clediscorde on 2015-10-23.
 */
public class BookRepositoryProxyTest {


    private BookRepository      bookRepository;
    private MongoTemplate       mongoTemplate;
    private BookRepositoryProxy bookRepositoryProxy;

    @Before
    public void setUp() throws Exception {
        bookRepository = mock(BookRepository.class);
        mongoTemplate = mock(MongoTemplate.class);
        bookRepositoryProxy = new BookRepositoryProxy(bookRepository, mongoTemplate);
    }

    @Test
    public void testSearchNoCriteria() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(0, query.getQueryObject().keySet().size());
    }

    @Test
    public void testSearchWithResult() throws Exception {
        Book book = mock(Book.class);
        when(book.getId()).thenReturn("Id");
        when(book.getTitle()).thenReturn("Title");
        when(book.getAuthor()).thenReturn("Author");
        when(book.getGenre()).thenReturn("Genre");
        when(book.getNumberOfPage()).thenReturn(10);
        when(book.getYearOfPublication()).thenReturn(1990);
        when(book.getOverallRating()).thenReturn(4);

        when(mongoTemplate.find(any(Query.class), eq(Book.class))).thenReturn(Collections.singletonList(book));

        BookCriteria    bookCriteria = getBookCriteria();
        List<BookModel> result       = bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(0, query.getQueryObject().keySet().size());

        assertEquals(1, result.size());
        assertEquals("Id", result.get(0).getId());
        assertEquals("Title", result.get(0).getTitle());
        assertEquals("Author", result.get(0).getAuthor());
        assertEquals("Genre", result.get(0).getGenre());
        assertEquals(10, result.get(0).getNumberOfPage());
        assertEquals(1990, result.get(0).getYearOfPublication());
        assertEquals(4, result.get(0).getOverallRating());
    }

    @Test
    public void testSearchAuthor() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        when(bookCriteria.getAuthor()).thenReturn("Author");
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(1, query.getQueryObject().keySet().size());
        assertEquals("Author", query.getQueryObject().get("author"));
    }

    @Test
    public void testSearchNumberOfPageMinMax() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        when(bookCriteria.getNumberOfPageMax()).thenReturn(100);
        when(bookCriteria.getNumberOfPageMin()).thenReturn(1);
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(1, query.getQueryObject().keySet().size());
        assertEquals("{ \"$gte\" : 1 , \"$lte\" : 100}", query.getQueryObject().get("numberOfPage").toString());
    }

    @Test
    public void testSearchNumberOfPageMin() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        when(bookCriteria.getNumberOfPageMin()).thenReturn(1);
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(1, query.getQueryObject().keySet().size());
        assertEquals("{ \"$gt\" : 1}", query.getQueryObject().get("numberOfPage").toString());
    }

    @Test
    public void testSearchPeriod() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        when(bookCriteria.getPeriod()).thenReturn(Period.CLASSIC);
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(1, query.getQueryObject().keySet().size());
        assertEquals("{ \"$gte\" : 1660 , \"$lte\" : 1715}", query.getQueryObject().get("yearOfPublication").toString());
    }

    @Test
    public void testSearchNumberOfPageMax() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        when(bookCriteria.getNumberOfPageMax()).thenReturn(100);
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(1, query.getQueryObject().keySet().size());
        assertEquals("{ \"$lte\" : 100}", query.getQueryObject().get("numberOfPage").toString());
    }

    @Test
    public void testSearchGenre() throws Exception {
        BookCriteria bookCriteria = getBookCriteria();
        when(bookCriteria.getGenre()).thenReturn("Genre");
        bookRepositoryProxy.search(bookCriteria);

        ArgumentCaptor<Query> queryArgumentCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryArgumentCaptor.capture(), eq(Book.class));

        Query query = queryArgumentCaptor.getValue();
        assertEquals(1, query.getQueryObject().keySet().size());
        assertEquals("Genre", query.getQueryObject().get("genre"));
    }

    private BookCriteria getBookCriteria() {
        BookCriteria bookCriteria = mock(BookCriteria.class);
        when(bookCriteria.getNumberOfPageMax()).thenReturn(null);
        when(bookCriteria.getNumberOfPageMin()).thenReturn(null);
        return bookCriteria;
    }

    @Test
    public void testSave() throws Exception {
        BookModel bookModelSource = mock(BookModel.class);
        when(bookModelSource.getTitle()).thenReturn("Title");
        when(bookModelSource.getAuthor()).thenReturn("Author");
        when(bookModelSource.getGenre()).thenReturn("Genre");
        when(bookModelSource.getNumberOfPage()).thenReturn(100);
        when(bookModelSource.getYearOfPublication()).thenReturn(1990);
        when(bookModelSource.getOverallRating()).thenReturn(5);

        Book bookSaved = mock(Book.class);
        when(bookSaved.getId()).thenReturn("NewId");
        when(bookRepository.save(any(Book.class))).thenReturn(bookSaved);

        BookModel bookModelResult = bookRepositoryProxy.save(bookModelSource);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book bookSource = bookArgumentCaptor.getValue();
        assertEquals("Title", bookSource.getTitle());
        assertEquals("Author", bookSource.getAuthor());
        assertEquals("Genre", bookSource.getGenre());
        assertEquals(100, bookSource.getNumberOfPage());
        assertEquals(1990, bookSource.getYearOfPublication());
        assertEquals(5, bookSource.getOverallRating());

        verify(bookModelResult).setId("NewId");
    }

    @Test
    public void testDelete() throws Exception {
        bookRepositoryProxy.delete("Id");
        verify(bookRepository).delete("Id");
    }

    @Test
    public void testGetAuthorList() throws Exception {
        DBCollection dbCollection = mock(DBCollection.class);
        when(dbCollection.distinct("author")).thenReturn(Arrays.asList("Author1", "Author2"));
        when(mongoTemplate.getCollection("book")).thenReturn(dbCollection);
        List<String> authors = bookRepositoryProxy.getAuthorList();

        verify(mongoTemplate).getCollection("book");
        verify(mongoTemplate.getCollection("book")).distinct("author");

        assertEquals(2, authors.size());
    }

    @Test
    public void testGetGenreList() throws Exception {
        DBCollection dbCollection = mock(DBCollection.class);
        when(dbCollection.distinct("genre")).thenReturn(Arrays.asList("Genre1", "Genre2"));
        when(mongoTemplate.getCollection("book")).thenReturn(dbCollection);
        List<String> genres = bookRepositoryProxy.getGenreList();

        verify(mongoTemplate).getCollection("book");
        verify(mongoTemplate.getCollection("book")).distinct("genre");

        assertEquals(2, genres.size());
    }
}