package com.example.urouteplanner.persistence.integration.twogis;

import lombok.Data;

@Data
public class Point {

    /**
     * Градусы восточной долготы.
     */
    private double lon;

    /**
     * Градусы северной широты.
     */
    private double lat;

    /**
     * Тип точки:
     *
     * <br> stop - начальная/конечная точка маршрута (прямой путь до дороги),
     * <br> walking - начальная/конечная точка маршрута (пешеходный путь до дороги),
     * <br> pref - промежуточная точка маршрута,
     */
    private String type;

    /**
     * Если true, точка является начальной точкой маршрута.
     * <br> Если параметр не указан, начальной точкой является первая точка в массиве.
     */
    private Boolean start;
}