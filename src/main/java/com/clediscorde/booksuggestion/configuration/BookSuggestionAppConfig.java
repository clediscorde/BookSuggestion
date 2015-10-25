package com.clediscorde.booksuggestion.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Created by clediscorde on 2015-10-21.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.clediscorde.booksuggestion")
public class BookSuggestionAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").resourceChain(true).addResolver(new PathResourceResolver());
        registry.addResourceHandler("/index.html").addResourceLocations("/").resourceChain(true).addResolver(new PathResourceResolver());
    }
}
