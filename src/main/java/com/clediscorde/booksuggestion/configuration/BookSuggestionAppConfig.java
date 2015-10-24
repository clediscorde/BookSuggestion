package com.clediscorde.booksuggestion.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by clediscorde on 2015-10-21.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.clediscorde.booksuggestion")
public class BookSuggestionAppConfig {

}
