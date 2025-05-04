package com.example.urouteplanner.mapper;

import com.example.urouteplanner.controller.UserSettingRequest;
import com.example.urouteplanner.service.dto.LocationDto;
import com.example.urouteplanner.service.dto.UserSettingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

@Mapper(componentModel = "spring")
public abstract class UserSettingMapper {

    @Autowired
    LocationExtractor locationExtractor;

    @Autowired
    TimeExtractor timeExtractor;

    @Mapping(target = "startPoint", source = "pointA", qualifiedByName = "extractLocation")
    @Mapping(target = "endPoint", source = "pointB", qualifiedByName = "extractLocation")
    @Mapping(target = "arrivalTime", source = "pointTime", qualifiedByName = "extractTime")
    public abstract UserSettingDto toMap(UserSettingRequest request);

    @Named("extractLocation")
    LocationDto extractLocation(String address) {
        return locationExtractor.extract(address);
    }

    @Named("extractTime")
    LocalTime extractTime(String time) {
        return timeExtractor.extract(time);
//        if (StringUtils.isBlank(time)) {
//            return null;
//        }
//
//        try {
//            return LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
//        } catch (DateTimeParseException ignored) {
//        }
//        try {
//            return LocalTime.parse(time, Constant.TIME_FORMATTER);
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Неверный формат времени: " + time);
//        }
    }
}
