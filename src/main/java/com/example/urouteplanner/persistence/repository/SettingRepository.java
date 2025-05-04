package com.example.urouteplanner.persistence.repository;

import com.example.urouteplanner.enums.ScheduleType;
import com.example.urouteplanner.persistence.entity.Route;
import com.example.urouteplanner.persistence.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
    Optional<Setting> findByRouteAndArrivalTimeAndBufferTimeAndScheduleType(
        Route route,
        LocalTime arrivalTime,
        int bufferTime,
        ScheduleType scheduleType);
}