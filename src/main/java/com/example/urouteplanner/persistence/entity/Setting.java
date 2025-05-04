package com.example.urouteplanner.persistence.entity;

import com.example.urouteplanner.enums.ScheduleType;
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
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "settings")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     * Маршрут (от точки A до точки B)
     */
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    /**
     * Время прибытия
     */
    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    /**
     * Буферное время перед выездом (в минутах).
     */
    @Column(name = "buffer_time", nullable = false)
    private Integer bufferTime;

    /**
     * Тип расписания
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "schedule_type", nullable = false)
    private ScheduleType scheduleType;
}
