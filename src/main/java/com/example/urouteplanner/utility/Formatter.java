package com.example.urouteplanner.utility;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class Formatter {

    private static final int MINUTES_PER_HOUR = 60;

    public String formatRouteDuration(int seconds) {
        Duration duration = Duration.ofSeconds(seconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % MINUTES_PER_HOUR;

        return hours > 0
            ? String.format("%d ч. %d мин.", hours, minutes)
            : String.format("%d мин.", minutes);
    }
}
