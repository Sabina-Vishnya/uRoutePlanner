package com.example.urouteplanner.persistence.integration.twogis;

import lombok.Data;

import java.util.List;

@Data
public class TwoGisResponse {
    private Query query;
    private List<RouteResult> result;
    private String status;
    private String type;
}