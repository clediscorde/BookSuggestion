package com.clediscorde.booksuggestion.repository;

import com.clediscorde.booksuggestion.dto.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by clediscorde on 2015-10-20.
 */
public interface BookRepository extends MongoRepository<Book, String> {
}
