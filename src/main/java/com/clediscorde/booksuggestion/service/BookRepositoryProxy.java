package com.clediscorde.booksuggestion.service;

import com.clediscorde.booksuggestion.dto.Book;
import com.clediscorde.booksuggestion.model.BookCriteria;
import com.clediscorde.booksuggestion.model.BookModel;
import com.clediscorde.booksuggestion.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by clediscorde on 2015-10-22.
 */
@Service
public class BookRepositoryProxy {

    public static final String BOOK_COLLECTION_NAME = "book";
    private final BookRepository bookRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookRepositoryProxy(BookRepository bookRepository, MongoTemplate mongoTemplate) {
        this.bookRepository = bookRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<BookModel> search(BookCriteria bookCriteria) {
        Query query = new Query();
        if (!StringUtils.isEmpty(bookCriteria.getAuthor())) {
            query.addCriteria(Criteria.where("author").is(bookCriteria.getAuthor()));
        }

        if (!StringUtils.isEmpty(bookCriteria.getGenre())) {
            query.addCriteria(Criteria.where("genre").is(bookCriteria.getGenre()));
        }

        if (bookCriteria.getNumberOfPageMin() != null && bookCriteria.getNumberOfPageMax() != null) {
            query.addCriteria(Criteria.where("numberOfPage").gte(bookCriteria.getNumberOfPageMin()).lte(bookCriteria.getNumberOfPageMax()));
        } else if (bookCriteria.getNumberOfPageMax() != null) {
            query.addCriteria(Criteria.where("numberOfPage").lte(bookCriteria.getNumberOfPageMax()));
        } else if (bookCriteria.getNumberOfPageMin() != null) {
            query.addCriteria(Criteria.where("numberOfPage").gte(bookCriteria.getNumberOfPageMin()));
        }

        if (bookCriteria.getPeriod() != null) {
            query.addCriteria(Criteria.where("yearOfPublication").gte(bookCriteria.getPeriod().getStart()).lte(bookCriteria.getPeriod().getEnd()));
        }

        if (!StringUtils.isEmpty(bookCriteria.getSortOrder())) {
            query.with(new Sort(bookCriteria.getSortOrder()));
        }

        return mongoTemplate.find(query, Book.class).stream().map(b -> {
            BookModel bookModel = new BookModel();
            bookModel.setId(b.getId());
            bookModel.setTitle(b.getTitle());
            bookModel.setAuthor(b.getAuthor());
            bookModel.setGenre(b.getGenre());
            bookModel.setNumberOfPage(b.getNumberOfPage());
            bookModel.setYearOfPublication(b.getYearOfPublication());
            bookModel.setOverallRating(b.getOverallRating());
            return bookModel;
        }).collect(toList());
    }

    public BookModel save(BookModel bookModel) {
        Book book       = new Book(bookModel.getId(), bookModel.getTitle(), bookModel.getAuthor(), bookModel.getGenre(), bookModel.getNumberOfPage(), bookModel.getYearOfPublication(), bookModel.getOverallRating());
        Book resultBook = bookRepository.save(book);
        bookModel.setId(resultBook.getId());

        return bookModel;
    }

    public void delete(String id) {
        bookRepository.delete(id);
    }

    public List<String> getAuthorList() {
        return mongoTemplate.getCollection(BOOK_COLLECTION_NAME).distinct("author");
    }

    public List<String> getGenreList() {
        return mongoTemplate.getCollection(BOOK_COLLECTION_NAME).distinct("genre");
    }
}
