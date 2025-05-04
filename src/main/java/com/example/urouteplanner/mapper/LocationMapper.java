package com.example.urouteplanner.mapper;

import com.example.urouteplanner.persistence.entity.Location;
import com.example.urouteplanner.service.dto.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "building", source = "house")
    @Mapping(target = "entrance", source = "block")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Location toEntity(LocationDto dto);
}