package com.example.urouteplanner.persistence.integration.dadata;

import lombok.Data;

@Data
public class DaDataResponse {

    /**
     * Исходный адрес одной строкой
     */
    private String source;

    /**
     * Стандартизованный адрес одной строкой
     */
    private String result;

    /**
     * Страна
     */
    private String timezone;

    /**
     * Страна
     */
    private String country;

    /**
     * Регион
     */
    private String region;

    /**
     * Город
     */
    private String city;

    /**
     * Улица
     */
    private String street;

    /**
     * Дом
     */
    private String house;

    /**
     * Корпус/строение
     */
    private String block;

    /**
     * Подъезд
     */
    private String entrance;

    /**
     * Код точности координат
     * <br> 0	Точные координаты
     * <br> 1	Ближайший дом
     * <br> 2	Улица
     * <br> 3	Населенный пункт
     * <br> 4	Город
     * <br> 5	Координаты не определены
     */
    private Integer qc_geo;

    /**
     * Координаты: широта
     */
    private String geo_lat;

    /**
     * Координаты: долгота
     */
    private String geo_lon;

    private Integer qc;

    private String fias_id;

    private String postal_code;
}
