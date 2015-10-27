package com.clediscorde.booksuggestion.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by clediscorde on 2015-10-20.
 * This class is used to configure MongoDB
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.clediscorde.booksuggestion.repository")
public class BookSuggestionMongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private ConfigurationService configurationService;

    @Override
    protected String getDatabaseName() {
        return configurationService.get("database.name", "booksuggestion");
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI(configurationService.get("database.uri", "mongodb://book:book@127.0.0.1:37234")));
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.clediscorde.booksuggestion.dto";
    }
}
