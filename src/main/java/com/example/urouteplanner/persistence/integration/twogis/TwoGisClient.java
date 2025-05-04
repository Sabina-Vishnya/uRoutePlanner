package com.example.urouteplanner.persistence.integration.twogis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
class TwoGisClient {

    @Value("${twogis.api.key}")
    private String apiKey;

    @Value("${twogis.api.url}")
    private String url;

    //todo retry
    public TwoGisResponse getRouting(Point startPoint, Point endPoint) {
        RestTemplate restTemplate = new RestTemplate();

        var response = restTemplate.exchange(
            url + apiKey,
            HttpMethod.POST,
            createRequest(startPoint, endPoint),
            TwoGisResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()
            || !"OK".equals(response.getBody().getStatus())
            || !"result".equals(response.getBody().getType())) {
            log.error("Ошибка при запросе к 2GIS API: {}", response.getStatusCode());
            throw new RuntimeException("Ошибка при запросе к 2GIS API");
        }
        return response.getBody();
    }

    private HttpEntity<TwoGisRequest> createRequest(Point startPoint, Point endPoint) {
        TwoGisRequest request = new TwoGisRequest();
        request.setPoints(List.of(startPoint, endPoint));
        request.setRoute_mode("fastest");
        request.setTraffic_mode("jam");
        request.setTransport("driving");
        request.setOutput("summary");
        return new HttpEntity<>(request);
    }
}

