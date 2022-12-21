package ru.smart_transportation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class SmartTransportationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartTransportationApplication.class, args);
    }
}
