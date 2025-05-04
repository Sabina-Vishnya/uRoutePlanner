package com.example.urouteplanner.mapper;

import com.example.urouteplanner.service.dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationMapperTest {
    private final LocationMapper mapper = Mappers.getMapper(LocationMapper.class);

    @Test
    void shouldMapCorrectly() {
        var dto = LocationDto.builder()
            .originalAddress("Москва, ул. Ленина 10, кв. 5")
            .normalizedAddress("Москва, ул. Ленина, 10, кв. 5")
            .timezone("UTC+3")
            .country("Россия")
            .city("Москва")
            .street("ул. Ленина")
            .house("10")
            .block("кв. 5")
            .entrance("2")
            .latitude(55.7558)
            .longitude(37.6173)
            .build();

        var entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getOriginalAddress()).isEqualTo(dto.getOriginalAddress());
        assertThat(entity.getNormalizedAddress()).isEqualTo(dto.getNormalizedAddress());
        assertThat(entity.getTimezone()).isEqualTo(dto.getTimezone());
        assertThat(entity.getCountry()).isEqualTo(dto.getCountry());
        assertThat(entity.getCity()).isEqualTo(dto.getCity());
        assertThat(entity.getStreet()).isEqualTo(dto.getStreet());
        assertThat(entity.getBuilding()).isEqualTo(dto.getHouse());
        assertThat(entity.getEntrance()).isEqualTo(dto.getBlock());
        assertThat(entity.getLatitude()).isEqualTo(dto.getLatitude());
        assertThat(entity.getLongitude()).isEqualTo(dto.getLongitude());
    }
}