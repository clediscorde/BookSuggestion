package com.clediscorde.booksuggestion.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by clediscorde on 2015-10-20.
 * This class is used to retrieve application configuration from config.properties.
 */
@Configuration
@PropertySource("classpath:config.properties")
public class ConfigurationService {

    @Autowired
    private Environment env;

    public String get(String key, String defaultValue) {
        return env.getProperty(key, defaultValue);
    }
}
