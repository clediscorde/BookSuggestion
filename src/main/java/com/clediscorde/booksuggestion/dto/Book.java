package com.clediscorde.booksuggestion.dto;

import org.springframework.data.annotation.Id;

/**
 * Created by clediscorde on 2015-10-20.
 * This class is the book database object
 */
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private int    numberOfPage;
    private int    yearOfPublication;
    private int    overallRating;

    public Book(String id, String title, String author, String genre, int numberOfPage, int yearOfPublication, int overallRating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numberOfPage = numberOfPage;
        this.yearOfPublication = yearOfPublication;
        this.overallRating = overallRating;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public int getOverallRating() {
        return overallRating;
    }
}
