package org.rxjava.gateway.admin;

import org.rxjava.api.user.inner.InnerUserApi;
import org.rxjava.apikit.client.ClientAdapter;
import org.rxjava.common.core.api.ReactiveHttpClientAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author happy 2019-03-17 22:10
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class RxGatewayAdminApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RxGatewayAdminApplication.class).web(WebApplicationType.REACTIVE).run(args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/goods/admin/**").filters(p -> p.stripPrefix(1)).uri("http://service-goods/"))
                .route(r -> r.path("/manager/admin/**").filters(p -> p.stripPrefix(1)).uri("http://service-manager/"))
                .route(r -> r.path("/user/admin/**").filters(p -> p.stripPrefix(1)).uri("http://localhost:8081/"))
                .route(r -> r.path("/order/admin/**").filters(p -> p.stripPrefix(1)).uri("http://service-order/"))
                .route(r -> r.path("/link/admin/**").filters(p -> p.stripPrefix(1)).uri("http://service-link/"))
                .build();
    }

    @Bean
    @Qualifier("userClientAdapter")
    public ClientAdapter userClientAdapter(
            @Qualifier("webFluxConversionService")
                    ConversionService conversionService,
            WebClient.Builder webClientBuilder
    ) {
        return ReactiveHttpClientAdapter.build(
                conversionService, webClientBuilder, "localhost:8081"
        );
    }

    @Bean
    public InnerUserApi serveUserApi(@Qualifier("userClientAdapter") ClientAdapter clientAdapter) {
        InnerUserApi innerUserApi = new InnerUserApi();
        innerUserApi.setclientAdapter(clientAdapter);
        return innerUserApi;
    }
}