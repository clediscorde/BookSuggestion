package com.clediscorde.booksuggestion.model.constant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by clediscorde on 2015-10-22.
 */
public class PeriodTest {
    @Test
    public void testClassic() throws Exception {
        assertEquals(1660, Period.CLASSIC.getStart());
        assertEquals(1715, Period.CLASSIC.getEnd());
    }

    @Test
    public void testModern() throws Exception {
        assertEquals(1880, Period.MODERN.getStart());
        assertEquals(1939, Period.MODERN.getEnd());
    }
}