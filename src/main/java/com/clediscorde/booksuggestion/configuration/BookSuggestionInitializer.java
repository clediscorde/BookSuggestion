package com.clediscorde.booksuggestion.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by clediscorde on 2015-10-21.
 * This class is used to configure the web application to use the Spring dispatch servlet.
 */
public class BookSuggestionInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{BookSuggestionAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{BookSuggestionAppConfig.class};
    }
}
