package com.example.urouteplanner.mapper;

import com.example.urouteplanner.service.dto.LocationDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class LocationExtractor {

    public LocationDto extract(String address) {
        if (StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("Адрес не может быть пустым");
        }

        String[] parts = address.split(",\\s*");

        if (parts.length < 3) {
            throw new IllegalArgumentException(MessageFormat.format("Неверный формат адреса {}", address));
        }

        return LocationDto.builder()
            .originalAddress(address)
            .normalizedAddress(address)
            .city(parts[0].trim())
            .street(parts[1].trim())
            .house(parts[2].trim())
            .country(parts.length > 3 ? parts[3].trim() : null)
            .build();
    }
}