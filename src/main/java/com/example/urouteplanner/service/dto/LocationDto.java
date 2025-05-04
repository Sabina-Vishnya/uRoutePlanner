package com.example.urouteplanner.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {
    private String normalizedAddress;
    private String originalAddress;
    private String timezone;
    private String country;
    private String city;
    private String street;
    private String house;
    private String block;
    private String entrance;
    private double latitude;
    private double longitude;
}