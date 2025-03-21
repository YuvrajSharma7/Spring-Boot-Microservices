package com.ps.microservices.api_gateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {
// Customizing request routing from API gateway to downstream microservices
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        // creating routeFunction separately and passing to RouteLocatorBuilder to build
//        Function<PredicateSpec, Buildable<Route>> routeFunction
//                = p->p.path("/get") //if get uri like http://localhost:8765/get
//                .filters(f->f
//                        .addRequestHeader("MyHeader","MyUri") //adding request header
//                        .addRequestParameter("Param","Value")) //adding request param
//                .uri("http://httpbin.org:80"); // redirect to this uri
//        return builder.routes()
//                .route(routeFunction)
//                .build();

        // directly passing routeFunctions to customize multiple routes
        return builder.routes()
                .route(p->p.path("/get") //if we hit uri like http://localhost:8765/get
                    .filters(f->f
                        .addRequestHeader("MyHeader","MyUri") //adding request header
                        .addRequestParameter("Param","Value")) //adding request param
                .uri("http://httpbin.org:80"))
                // we can implement custom routing as below, before that we have to remove
                // spring.cloud.gateway.discovery.locator properties from application.properties
                .route(p->p.path("/currency-exchange/**") //request uri is of this pattern
                        .uri("lb://currency-exchange/")) // redirect with load balanced to service registered with the
                // name 'currency-exchange', talking to the naming/eureka server
                .route(p->p.path("/currency-conversion/**")//request uri is of this pattern
                        .uri("lb://currency-conversion/")) // redirect with load balanced to service registered with the
                // name 'currency-conversion', talking to the naming/eureka server
                .route(p->p.path("/currency-conversion-feign/**")//request uri is of this pattern
                        .uri("lb://currency-conversion/")) // redirect with load balanced to service registered with the
                // name 'currency-conversion', talking to the naming/eureka server
                .route(p->p.path("/currency-conversion-new/**")//request uri is of this pattern
                        .filters(f->f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)", // source uri (replace this uri with)
                                // (?<segment>.*) is regex to identify remaining uri part as segment
                                "/currency-conversion-feign/${segment}" // target uri (this target uri)
                                // using ${segment} we are adding that segment to this url
                        ))
                        .uri("lb://currency-conversion/")) //redirect to this
                .build();
    }
}
