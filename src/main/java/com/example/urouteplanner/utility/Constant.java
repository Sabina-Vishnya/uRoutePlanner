package com.example.urouteplanner.utility;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class Constant {
    public final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public final int BUFFER_TIME_IN_SECONDS = 1800;

}
