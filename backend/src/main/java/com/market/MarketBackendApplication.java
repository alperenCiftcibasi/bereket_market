package com.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(title = "Market API", version = "1.0", description = "Market uygulaması API dökümantasyonu")
	)

@SpringBootApplication
public class MarketBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketBackendApplication.class, args);
    }
}
