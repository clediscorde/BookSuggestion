package com.clediscorde.booksuggestion.configuration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class ConfigurationServiceTest {

    private Environment          environment;
    private ConfigurationService configurationService;

    @Before
    public void setUp() throws Exception {
        environment = mock(Environment.class);
        configurationService = new ConfigurationService();
        ReflectionTestUtils.setField(configurationService, "env", environment);
    }

    @Test
    public void testGet() throws Exception {
        when(environment.getProperty("key", "default")).thenReturn("value");
        assertEquals("value", configurationService.get("key", "default"));
    }
}