package com.example.urouteplanner.persistence.integration.dadata;

import com.example.urouteplanner.service.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DaDataService {

    private final DaDataClient daDataClient;
    private final DaDataMapper daDataMapper;

    public LocationDto getRouteDuration(String address) {
        return daDataMapper.toDto(daDataClient.getGeocodedData(address));
    }
}
