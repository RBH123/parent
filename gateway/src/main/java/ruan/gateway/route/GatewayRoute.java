package ruan.gateway.route;

import lombok.SneakyThrows;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: rocky
 * Date: 2021/2/26 9:57
 * Project: parent1
 * Package: ruan.gateway.route
 */
@Configuration
public class GatewayRoute {

    @Bean
    public RouteLocator addRoute(RouteLocatorBuilder builder){
        return builder.routes().route("comsumer-service",p->p.path("/comsumer/**/*").uri("lb://comsumer-service")).build();
    }
}
