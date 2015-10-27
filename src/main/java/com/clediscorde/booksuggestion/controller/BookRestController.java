package com.clediscorde.booksuggestion.controller;

import com.clediscorde.booksuggestion.exception.InvalidBookException;
import com.clediscorde.booksuggestion.model.BookCriteria;
import com.clediscorde.booksuggestion.model.BookModel;
import com.clediscorde.booksuggestion.service.BookRepositoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by clediscorde on 2015-10-21.
 * This class is the Rest controller used to access the book database
 */
@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookRepositoryProxy bookRepositoryProxy;

    @Autowired
    public BookRestController(BookRepositoryProxy bookRepositoryProxy) {
        this.bookRepositoryProxy = bookRepositoryProxy;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<BookModel> searchBook(@ModelAttribute BookCriteria bookCriteria) {
        return bookRepositoryProxy.search(bookCriteria);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BookModel save(@Valid @RequestBody BookModel bookModel, BindingResult result) throws InvalidBookException {
        if (result.hasErrors()) {
            throw new InvalidBookException("Invalid book: " + result.getFieldErrors().stream().map(error -> "\n" + error.getField() + ":" + error.getDefaultMessage()).collect(Collectors.joining()));
        }

        return bookRepositoryProxy.save(bookModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id) throws InvalidBookException {
        bookRepositoryProxy.delete(id);
        return "ok";
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public List<String> getAuthors() throws InvalidBookException {
        return bookRepositoryProxy.getAuthorList();
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public List<String> getGenres() throws InvalidBookException {
        return bookRepositoryProxy.getGenreList();
    }
}
