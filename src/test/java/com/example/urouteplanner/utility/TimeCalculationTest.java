package com.example.urouteplanner.utility;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeCalculationTest {

    @Test
    void calculateAlertTimeTest() {
        LocalTime arrivalTime = LocalTime.of(10, 0);
        LocalTime result = TimeCalculation.calculateAlertTime(arrivalTime, 900, 1800);
        assertEquals(LocalTime.of(9, 15), result);

    }
}