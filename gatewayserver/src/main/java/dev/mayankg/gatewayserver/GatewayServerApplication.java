package dev.mayankg.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator fvnBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/fvnbank/accounts/**")
                        .filters(f -> f.rewritePath("/fvnbank/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p
                        .path("/fvnbank/loans/**")
                        .filters(f -> f.rewritePath("/fvnbank/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS"))
                .route(p -> p
                        .path("/fvnbank/cards/**")
                        .filters(f -> f.rewritePath("/fvnbank/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARDS"))
                .build();
    }
}