package com.example.urouteplanner.persistence.repository;

import com.example.urouteplanner.enums.TransportType;
import com.example.urouteplanner.persistence.entity.Location;
import com.example.urouteplanner.persistence.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    Optional<Route> findByFromLocationAndToLocationAndTransportType(
        Location fromLocation,
        Location toLocation,
        TransportType transportType);
}