package com.mvc.login.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class WebConfig {

	/*Mono<ServerResponse> testMessage(ServerRequest request) {

		return ServerResponse.ok().body(Mono.just("this is test message!"), String.class);
	}

	@Bean
	RouterFunction<?> routes() {

		return route(GET("/test"), this::testMessage);
	}*/

}