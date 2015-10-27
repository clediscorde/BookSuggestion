package com.clediscorde.booksuggestion.model;

import com.clediscorde.booksuggestion.model.constant.Period;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Created by clediscorde on 2015-10-22.
 * Book search criteria
 */
public class BookCriteria {
    private String   author;
    private String   genre;
    private Integer  numberOfPageMin;
    private Integer  numberOfPageMax;
    private Period   period;
    private String[] sortOrder;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String[] getSortOrder() {
        return sortOrder != null ? Arrays.stream(sortOrder).filter(s -> !StringUtils.isEmpty(s)).toArray(String[]::new) : null;
    }

    public void setSortOrder(String sortOrder[]) {
        this.sortOrder = sortOrder;
    }
}
