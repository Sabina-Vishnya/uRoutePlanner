package com.example.urouteplanner.persistence.integration.dadata;

import com.example.urouteplanner.service.dto.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface DaDataMapper {

    @Mapping(target = "originalAddress", source = "source")
    @Mapping(target = "normalizedAddress", source = "result")
    @Mapping(target = "latitude", source = "geo_lat")
    @Mapping(target = "longitude", source = "geo_lon")
    @Mapping(target = "city", source = "region")
    LocationDto toDto(DaDataResponse response);

}