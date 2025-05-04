package com.example.urouteplanner.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_date", updatable = false)
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    private String country;

    private String city;

    private String street;

    private String building;

    private String entrance;

    /**
     * Широта (latitude) для геолокации.
     */
    private double latitude;

    /**
     * Долгота (longitude) для геолокации.
     */
    private double longitude;

    /**
     * Часовой пояс (например, UTC+3).
     */
    private String timezone;

    /**
     * Оригинальный адрес до нормализации
     */
    @Column(name = "original_address")
    private String originalAddress;

    /**
     * Нормализованный адрес
     */
    @Column(name = "normalized_address")
    private String normalizedAddress;
}
