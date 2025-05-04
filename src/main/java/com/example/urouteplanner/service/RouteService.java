package com.example.urouteplanner.service;

import com.example.urouteplanner.enums.TransportType;
import com.example.urouteplanner.persistence.entity.Location;
import com.example.urouteplanner.persistence.entity.Route;
import com.example.urouteplanner.persistence.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    Route getOrSaveRoute(Location startLocation, Location endLocation) {
        return routeRepository.findByFromLocationAndToLocationAndTransportType(
            startLocation,
            endLocation,
            TransportType.DRIVING
        ).orElseGet(() -> {
            Route newRoute = new Route();
            newRoute.setFromLocation(startLocation);
            newRoute.setToLocation(endLocation);
            newRoute.setTransportType(TransportType.DRIVING); // todo пока всегда driving?
            routeRepository.save(newRoute);
            return newRoute;
        });
    }
}
