package com.example.urouteplanner.mapper;

import com.example.urouteplanner.utility.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
class TimeExtractor {

    LocalTime extract(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }

        List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ofPattern("H:mm"),
            Constant.TIME_FORMATTER
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalTime.parse(time, formatter);
            } catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("Неверный формат времени: " + time);
    }
}
