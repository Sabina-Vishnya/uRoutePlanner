package com.example.urouteplanner.persistence.integration.twogis;

import lombok.Data;

import java.util.List;

@Data
public class TwoGisRequest {
    private List<Point> points;
    private String route_mode;
    private String traffic_mode;
    private String transport;
    private String output;
}
