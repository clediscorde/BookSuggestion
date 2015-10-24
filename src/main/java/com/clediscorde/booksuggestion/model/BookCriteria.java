package com.clediscorde.booksuggestion.model;

import com.clediscorde.booksuggestion.model.constant.Period;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class BookCriteria {
    private String  Author;
    private String  genre;
    private Integer numberOfPageMin;
    private Integer numberOfPageMax;
    private Period  period;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getNumberOfPageMin() {
        return numberOfPageMin;
    }

    public void setNumberOfPageMin(Integer numberOfPageMin) {
        this.numberOfPageMin = numberOfPageMin;
    }

    public Integer getNumberOfPageMax() {
        return numberOfPageMax;
    }

    public void setNumberOfPageMax(Integer numberOfPageMax) {
        this.numberOfPageMax = numberOfPageMax;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
