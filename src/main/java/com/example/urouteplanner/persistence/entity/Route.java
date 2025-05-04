package com.example.urouteplanner.persistence.entity;

import com.example.urouteplanner.enums.TransportType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     * Начальная точка маршрута
     */
    @ManyToOne
    @JoinColumn(name = "from_location_id", nullable = false)
    private Location fromLocation;

    /**
     * Конечная точка маршрута
     */
    @ManyToOne
    @JoinColumn(name = "to_location_id", nullable = false)
    private Location toLocation;

    /**
     * Тип транспорта
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transport_type", nullable = false)
    private TransportType transportType;
}