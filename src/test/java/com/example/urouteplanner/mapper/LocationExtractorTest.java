package com.example.urouteplanner.mapper;

import com.example.urouteplanner.service.dto.LocationDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LocationExtractorTest {

    private final LocationExtractor extractor = new LocationExtractor();

    private static Stream<Arguments> provideValidAddresses() {
        return Stream.of(
            Arguments.of(
                "Город, Улица, 10",
                "Город", "Улица", "10", null),
            Arguments.of(
                "г. Город, ул. Улица, д. 10",
                "г. Город", "ул. Улица", "д. 10", null),
            Arguments.of(
                "Страна, Город, Улица, 1",
                "Страна", "Город", "Улица", "1"),
            Arguments.of(
                "  Город  ,  Улица  ,  10  ",
                "Город", "Улица", "10", null)
        );
    }

    @ParameterizedTest()
    @MethodSource("provideValidAddresses")
    void shouldExtractCorrectly(String rawAddress, String city, String street, String house, String country) {
        LocationDto result = extractor.extract(rawAddress);

        assertThat(result)
            .extracting(
                LocationDto::getNormalizedAddress,
                LocationDto::getOriginalAddress,
                LocationDto::getCity,
                LocationDto::getStreet,
                LocationDto::getHouse,
                LocationDto::getCountry
            )
            .containsExactly(
                rawAddress,
                rawAddress,
                city,
                street,
                house,
                country
            );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
        " ",
        "Москва, Ленина уже нет",
        "Москва Ленина 5",
    })
    void shouldThrowWhenAddressInvalid(String value) {
        assertThatThrownBy(() -> extractor.extract(value));
    }

}