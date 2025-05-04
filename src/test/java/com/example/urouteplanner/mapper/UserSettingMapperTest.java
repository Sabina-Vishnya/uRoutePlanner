package com.example.urouteplanner.mapper;

import com.example.urouteplanner.controller.UserSettingRequest;
import com.example.urouteplanner.service.dto.LocationDto;
import com.example.urouteplanner.service.dto.UserSettingDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSettingMapperTest {

    private final static String ADDRESS_1 = "Москва, Беломорканал, 7";
    private final static String ADDRESS_2 = "Стьюджон, Какой-то проспект, 33";

    @InjectMocks
    private UserSettingMapperImpl sut;

    @Mock
    private LocationExtractor locationExtractor;

    @Mock
    private TimeExtractor timeExtractor;

    @Test
    void shouldMapCorrectly() {
        UserSettingRequest request = new UserSettingRequest(
            254471470L,
            "sabina_vishnya",
            ADDRESS_1,
            ADDRESS_2,
            "09:00"
        );
        var locationDto1 = LocationDto.builder()
            .normalizedAddress(ADDRESS_1)
            .city("Москва")
            .street("Беломорканал")
            .house("7")
            .build();

        var locationDto2 = LocationDto.builder()
            .normalizedAddress(ADDRESS_2)
            .city("Стьюджон")
            .street("Какой-то проспект")
            .house("33")
            .build();

        when(locationExtractor.extract(request.getPointA())).thenReturn(locationDto1);
        when(locationExtractor.extract(request.getPointB())).thenReturn(locationDto2);
        when(timeExtractor.extract(request.getPointTime())).thenReturn(LocalTime.of(9, 0));

        UserSettingDto dto = sut.toMap(request);

        assertEquals(254471470L, dto.getChatId());
        assertEquals("sabina_vishnya", dto.getUsername());
        assertEquals(LocalTime.of(9, 0), dto.getArrivalTime());

        assertNull(dto.getStartPoint().getCountry());
        assertEquals(ADDRESS_1, dto.getStartPoint().getNormalizedAddress());
        assertEquals("Москва", dto.getStartPoint().getCity());
        assertEquals("Беломорканал", dto.getStartPoint().getStreet());
        assertEquals("7", dto.getStartPoint().getHouse());
        assertNull(dto.getStartPoint().getEntrance());

        assertNull(dto.getEndPoint().getCountry());
        assertEquals(ADDRESS_2, dto.getEndPoint().getNormalizedAddress());
        assertEquals("Стьюджон", dto.getEndPoint().getCity());
        assertEquals("Какой-то проспект", dto.getEndPoint().getStreet());
        assertEquals("33", dto.getEndPoint().getHouse());
        assertNull(dto.getEndPoint().getEntrance());
    }
}