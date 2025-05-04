package com.example.urouteplanner.utility;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {

    @ParameterizedTest
    @CsvSource({
        "1800, 30 мин.",
        "7200, 2 ч. 0 мин.",
        "5400, 1 ч. 30 мин."
    })
    void formatRouteDurationTest(int seconds, String expectedStr) {
        assertEquals(expectedStr, Formatter.formatRouteDuration(seconds));
        assertEquals(expectedStr, Formatter.formatRouteDuration(seconds));
        assertEquals(expectedStr, Formatter.formatRouteDuration(seconds));
    }
}