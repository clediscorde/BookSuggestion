package com.clediscorde.booksuggestion.controller;

import com.clediscorde.booksuggestion.model.BookCriteria;
import com.clediscorde.booksuggestion.model.BookModel;
import com.clediscorde.booksuggestion.model.constant.Period;
import com.clediscorde.booksuggestion.service.BookRepositoryProxy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by clediscorde on 2015-10-23.
 */
public class BookRestControllerTest {

    private MockMvc mockMvc;

    private BookRepositoryProxy repositoryProxy;
    private BookRestController  controller;

    @Before
    public void setUp() throws Exception {
        repositoryProxy = mock(BookRepositoryProxy.class);
        controller = new BookRestController(repositoryProxy);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSearchBook() throws Exception {
        String uri = "/books/search?author=AUTHOR&genre=GENRE&numberOfPageMin=100&numberOfPageMax=200&period=CLASSIC";
        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        ArgumentCaptor<BookCriteria> bookCriteriaArgumentCaptor = ArgumentCaptor.forClass(BookCriteria.class);
        verify(repositoryProxy).search(bookCriteriaArgumentCaptor.capture());

        BookCriteria bookCriteria = bookCriteriaArgumentCaptor.getValue();
        assertEquals("AUTHOR", bookCriteria.getAuthor());
        assertEquals("GENRE", bookCriteria.getGenre());
        assertEquals(100, bookCriteria.getNumberOfPageMin().intValue());
        assertEquals(200, bookCriteria.getNumberOfPageMax().intValue());
        assertEquals(Period.CLASSIC, bookCriteria.getPeriod());
    }

    @Test
    public void testDelete() throws Exception {
        String uri = "/books/delete/12345";
        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(repositoryProxy).delete("12345");
    }

    @Test
    public void testSave() throws Exception {
        String uri = "/books/save";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content("{\"author\": \"Jean-Francois Harvey\",\n" +
                                                                                  "    \"title\": \"Courir mieux\",\n" +
                                                                                  "    \"genre\": \"Sport\",\n" +
                                                                                  "    \"numberOfPage\": 312,\n" +
                                                                                  "    \"yearOfPublication\": 2013,\n" +
                                                                                  "    \"overallRating\": 5}")).andExpect(status().isOk());

        ArgumentCaptor<BookModel> bookModelArgumentCaptor = ArgumentCaptor.forClass(BookModel.class);
        verify(repositoryProxy).save(bookModelArgumentCaptor.capture());

        BookModel bookModel = bookModelArgumentCaptor.getValue();
        assertEquals("Jean-Francois Harvey", bookModel.getAuthor());
        assertEquals("Courir mieux", bookModel.getTitle());
        assertEquals("Sport", bookModel.getGenre());
        assertEquals(312, bookModel.getNumberOfPage());
        assertEquals(2013, bookModel.getYearOfPublication());
        assertEquals(5, bookModel.getOverallRating());
    }

    @Test(expected = NestedServletException.class)
    public void testSaveWithError() throws Exception {
        String uri = "/books/save";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content("{\"author\": \"\",\n" +
                                                                                  "    \"title\": \"Courir mieux\",\n" +
                                                                                  "    \"genre\": \"\",\n" +
                                                                                  "    \"numberOfPage\": 312,\n" +
                                                                                  "    \"yearOfPublication\": 2013,\n" +
                                                                                  "    \"overallRating\": 5}")).andExpect(status().isOk());

        ArgumentCaptor<BookModel> bookModelArgumentCaptor = ArgumentCaptor.forClass(BookModel.class);
        verify(repositoryProxy).save(bookModelArgumentCaptor.capture());

    }

    @Test
    public void testGetAuthors() throws Exception {
        String uri = "/books/authors";
        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(repositoryProxy).getAuthorList();
    }

    @Test
    public void testGetGenres() throws Exception {
        String uri = "/books/genres";
        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(repositoryProxy).getGenreList();
    }
}