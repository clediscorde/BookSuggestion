package com.clediscorde.booksuggestion.configuration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class BookSuggestionMongoConfigTest {

    private BookSuggestionMongoConfig mongoConfig;
    private ConfigurationService      configurationService;

    @Before
    public void setUp() throws Exception {
        configurationService = mock(ConfigurationService.class);
        mongoConfig = new BookSuggestionMongoConfig();
        ReflectionTestUtils.setField(mongoConfig, "configurationService", configurationService);
    }

    @Test
    public void testGetDatabaseName() throws Exception {
        when(configurationService.get("database.name", "booksuggestion")).thenReturn("databasename");
        assertEquals("databasename", mongoConfig.getDatabaseName());
    }

    @Test
    public void testGetMappingBasePackage() throws Exception {
        assertEquals("com.clediscorde.booksuggestion.dto", mongoConfig.getMappingBasePackage());
    }
}