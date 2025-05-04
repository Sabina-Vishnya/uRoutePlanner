package com.example.urouteplanner.persistence.integration.twogis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwoGisService {
    private final TwoGisClient twoGisClient;
    public int getRouteDurationInSeconds(Point startPoint, Point endPoint) {
        TwoGisResponse response = twoGisClient.getRouting(startPoint, endPoint);
        return response.getResult().get(0).getDuration();
    }
}
