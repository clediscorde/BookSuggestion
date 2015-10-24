package com.clediscorde.booksuggestion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by clediscorde on 2015-10-24.
 */
@ControllerAdvice
public class BookControllerAdvice {

    private final static Logger LOG = LoggerFactory.getLogger(BookControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {
        LOG.error("Exception: ", e);
        return e.getMessage();
    }
}
