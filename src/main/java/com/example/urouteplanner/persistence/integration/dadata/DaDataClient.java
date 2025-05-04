package com.example.urouteplanner.persistence.integration.dadata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class DaDataClient {
    @Value("${dadata.api.key}")
    private String apiKey;
    @Value("${dadata.api.secret}")
    private String apiSecret;
    @Value("${dadata.api.url}")
    private String url;

    //todo retry
    public DaDataResponse getGeocodedData(String address) {
        RestTemplate restTemplate = new RestTemplate();

        var response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            createRequest(address),
            DaDataResponse[].class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Ошибка запроса к DaData. Статус: {}", response.getStatusCode());
            throw new RuntimeException("Ошибка при запросе к DaData API");
        }

        if (response.getBody().length == 0) {
            log.error("Из DaData API вернулся пустой массив для адреса: {}", address);
            throw new RuntimeException("Адрес не распознан");
        }
        return response.getBody()[0];
    }

    private HttpEntity<List<String>> createRequest(String address) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + apiKey);
        headers.set("X-Secret", apiSecret);
        return new HttpEntity<>(List.of(address), headers);
    }
}