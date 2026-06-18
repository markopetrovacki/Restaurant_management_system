package com.restaurant.gateway_service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class HealthCheckConfiguration {
	
	 private static final Logger LOG = LoggerFactory.getLogger(HealthCheckConfiguration.class);

	// private HealthAggregator healthAggregator;

	 private final WebClient.Builder webClientBuilder;

	 private WebClient webClient;
	 
	 @Autowired
     public HealthCheckConfiguration(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
     }
	 
	 @Bean
     public ReactiveHealthContributor healthcheckMicroservices() {
        Map<String, ReactiveHealthIndicator> indicators = new LinkedHashMap<>();

        // Prilagodi listu tvojim mikroservisima (imena moraju odgovarati onima u Eureki)
        indicators.put("user-service",          () -> getHealth("http://user-service"));
        indicators.put("restauration-service",  () -> getHealth("http://restauration-service"));
        indicators.put("reservation-service",   () -> getHealth("http://reservation-service"));
        indicators.put("review-service",        () -> getHealth("http://review-service"));
        indicators.put("notification-service",  () -> getHealth("http://notification-service"));

        // Novi, moderan način za kreiranje kompozitnog indikatora
        return CompositeReactiveHealthContributor.fromMap(indicators);
     }
	 
	 private Mono<Health> getHealth(String url) {
        String healthUrl = url + "/actuator/health";
        LOG.debug("Pozivam Health API na URL-u: {}", healthUrl);
        
        return getWebClient().get()
                .uri(healthUrl)
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> new Health.Builder().up().build())
                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()))
                .log();
     }

     private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
     }
}
