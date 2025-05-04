package com.example.urouteplanner.persistence.repository;

import com.example.urouteplanner.persistence.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByCityAndStreetAndBuilding(String city, String street, String building);
    Optional<Location> findByOriginalAddress(String originalAddress);
}
