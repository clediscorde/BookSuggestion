package com.clediscorde.booksuggestion.dto;

/**
 * Created by clediscorde on 2015-10-20.
 */
public class Book {
    private String author;
    private String genre;
    private int numberOfPage;
    private int yearOfPublication;
    private int overallRating;

    public Book(String author, String genre, int numberOfPage, int yearOfPublication, int overallRating) {
        this.author = author;
        this.genre = genre;
        this.numberOfPage = numberOfPage;
        this.yearOfPublication = yearOfPublication;
        this.overallRating = overallRating;
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
