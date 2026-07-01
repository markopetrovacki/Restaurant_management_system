package com.restaurant.restaurant_composite_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages = "com.restaurant")
@EnableDiscoveryClient
public class RestaurantCompositeServiceApplication {

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		final WebClient.Builder builder = WebClient.builder();
		return builder;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestaurantCompositeServiceApplication.class, args);
	}

}
