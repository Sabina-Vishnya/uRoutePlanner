package com.example.urouteplanner.service.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UserSettingDto {
    private long chatId;
    private String username;
    private LocationDto startPoint;
    private LocationDto endPoint;
    private LocalTime arrivalTime;
}
