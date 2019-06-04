package org.rxjava.gateway.manager;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author happy 2019-03-17 22:10
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class RxManagerGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RxManagerGatewayApplication.class).web(WebApplicationType.REACTIVE).run(args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/goods/admin/**").filters(p -> p.stripPrefix(1)).uri("lb://service-goods/"))
                .route(r -> r.path("/manager/admin/**").filters(p -> p.stripPrefix(1)).uri("lb://service-manager/"))
                .route(r -> r.path("/user/admin/**").filters(p -> p.stripPrefix(1)).uri("lb://service-user/"))
                .route(r -> r.path("/order/admin/**").filters(p -> p.stripPrefix(1)).uri("lb://service-order/"))
                .route(r -> r.path("/link/admin/**").filters(p -> p.stripPrefix(1)).uri("lb://service-link/"))
                .build();
    }
}
