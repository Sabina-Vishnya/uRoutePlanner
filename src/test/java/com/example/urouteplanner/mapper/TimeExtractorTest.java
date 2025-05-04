package com.example.urouteplanner.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeExtractorTest {

    private final TimeExtractor extractor = new TimeExtractor();

    @ParameterizedTest
    @ValueSource(strings = {"9:00", "09:00"})
    void shouldReturnExtractWhenCorrectTime(String value) {
        LocalTime expected = LocalTime.of(9, 0);
        assertEquals(expected, extractor.extract(value));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnNullWhenNullOrBlank(String value) {
        assertNull(extractor.extract(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9-00", "25:00", "smth"})
    void extractInvalidTest(String value) {
        assertThrows(IllegalArgumentException.class, () -> extractor.extract(value));
    }
}