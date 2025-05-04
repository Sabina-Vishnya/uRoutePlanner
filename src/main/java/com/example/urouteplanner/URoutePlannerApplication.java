package com.example.urouteplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableJpaRepositories
@SpringBootApplication
public class URoutePlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(URoutePlannerApplication.class, args);
    }

}
