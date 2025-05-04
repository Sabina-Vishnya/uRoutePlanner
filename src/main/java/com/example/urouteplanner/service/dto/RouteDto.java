package com.example.urouteplanner.service.dto;

import com.example.urouteplanner.persistence.integration.twogis.Point;
import lombok.Data;

@Data
public class RouteDto {
    //todo add PointDto for domain instead of Point for persistence
    private Point startPoint;
    private Point endPoint;
}
