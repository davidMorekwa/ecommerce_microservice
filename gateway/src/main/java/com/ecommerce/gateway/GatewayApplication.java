package com.ecommerce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/auth/login", "/auth/register", "/user/me")
						.uri("lb://user-service")
					)
				.route(r -> r.path("/product")
						.filters(f -> f.rewritePath("/product/(?<segment>.*)", "api/v1/product/${segment}"))
						.uri("lb://product-service")
					)
				.route(r -> r.path("/api/v1/order", "/api/v1/order/**")
						.uri("lb://order-service")
					)
				.build();
	}

}
