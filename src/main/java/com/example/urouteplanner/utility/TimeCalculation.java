package com.example.urouteplanner.utility;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class TimeCalculation {

    public LocalTime calculateAlertTime(LocalTime arrivalTime, int routeDurationInSeconds, int bufferSeconds) {
        return arrivalTime.minusSeconds(routeDurationInSeconds).minusSeconds(bufferSeconds);
    }

}