package com.example.urouteplanner.service;

import com.example.urouteplanner.mapper.LocationMapper;
import com.example.urouteplanner.persistence.entity.Location;
import com.example.urouteplanner.persistence.integration.dadata.DaDataService;
import com.example.urouteplanner.persistence.repository.LocationRepository;
import com.example.urouteplanner.service.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final DaDataService daDataService;
    private final LocationMapper locationMapper;

    Location getOrSaveLocation(LocationDto locationDto) {
        return locationRepository.findByCityAndStreetAndBuilding(
                locationDto.getCity(),
                locationDto.getStreet(),
                locationDto.getHouse())
            .orElseGet(() -> locationRepository.findByOriginalAddress(locationDto.getOriginalAddress())
                .orElseGet(() -> {
                    var dto = daDataService.getRouteDuration(locationDto.getNormalizedAddress());
                    var location = locationMapper.toEntity(dto);
                    locationRepository.save(location);
                    return location;
                }));
    }
}
