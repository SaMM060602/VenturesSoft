package com.sandy.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.disable())
	        .authorizeExchange(exchange -> exchange
	            .pathMatchers(HttpMethod.OPTIONS).permitAll()
	            .pathMatchers(
	            		"/api/swagger-ui.html",
	                    "/api/swagger-ui/**",
	                    "/api/v3/api-docs",
	                    "/api/monedas/v3/api-docs",
		                "/api/monedas/swagger-ui/**",
		                "/api/empleados/v3/api-docs",
		                "/api/empleados/swagger-ui/**",
	                    "/v3/api-docs/**"
	            ).permitAll()
	            .anyExchange().authenticated()
	        )
	        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

	    return http.build();
	}

}