package com.clediscorde.booksuggestion.model.constant;

/**
 * Created by clediscorde on 2015-10-22.
 */
public enum Period {

    // https://fr.wikipedia.org/wiki/Classicisme
    CLASSIC(1660, 1715),
    // https://en.wikipedia.org/wiki/Literary_modernism
    MODERN(1880, 1939);

    private final int start;
    private final int end;

    Period(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
