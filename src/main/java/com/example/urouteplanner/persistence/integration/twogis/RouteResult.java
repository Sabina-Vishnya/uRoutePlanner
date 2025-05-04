package com.example.urouteplanner.persistence.integration.twogis;

import lombok.Data;

@Data
public class RouteResult {

    /**
     * Время в пути в секундах.
     */
    private int duration;

    /**
     * Длина маршрута в метрах.
     */
    private int length;
}