package com.sandy.empleados.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.sandy.empleados.dto.MonedaResponse;

import java.util.Optional;


@Component
public class MonedaClient {
	private final WebClient webClient;

	public MonedaClient(WebClient.Builder builder) {
        this.webClient = builder
            .baseUrl("http://localhost:8081")
            .build();
    }

	 public boolean existeClave(Long numCia, String claveMoneda) {
        try {
            // Obtener el token JWT del contexto de seguridad
            String token = null;
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof JwtAuthenticationToken jwtAuth) {
                token = jwtAuth.getToken().getTokenValue();
            }
            if (token == null) {
                throw new RuntimeException("No se pudo obtener el token JWT del contexto de seguridad");
            }
            webClient.get()
                     .uri("/api/monedas/{numCia}/{claveMoneda}", numCia, claveMoneda)
                     .header("Authorization", "Bearer " + token)
                     .retrieve()
                     .bodyToMono(String.class)
                     .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error al validar la moneda: " + e.getMessage(), e);
        }
    }
	 
	 public Optional<MonedaResponse> existeMoneda(Long numCia, String claveMoneda) {
	        try {
	            String token = null;
	            var auth = SecurityContextHolder.getContext().getAuthentication();
	            if (auth instanceof JwtAuthenticationToken jwtAuth) {
	                token = jwtAuth.getToken().getTokenValue();
	            }
	            if (token == null) {
	                throw new RuntimeException("No se pudo obtener el token JWT del contexto de seguridad");
	            }
	            MonedaResponse response = webClient.get()
	                .uri("/api/monedas/{numCia}/{claveMoneda}", numCia, claveMoneda)
	                .header("Authorization", "Bearer " + token)
	                .retrieve()
	                .bodyToMono(MonedaResponse.class)
	                .block();
	            return Optional.ofNullable(response);
	        } catch (WebClientResponseException.NotFound e) {
	            return Optional.empty();
	        } catch (Exception e) {
	            throw new RuntimeException("Error al consultar la moneda: " + e.getMessage(), e);
	        }
	    }

	    public Optional<MonedaResponse> existeMonedaEmp(Long numCia, String claveMoneda) {
	        try {
	            String token = null;
	            var auth = SecurityContextHolder.getContext().getAuthentication();
	            if (auth instanceof JwtAuthenticationToken jwtAuth) {
	                token = jwtAuth.getToken().getTokenValue();
	            }
	            if (token == null) {
	                throw new RuntimeException("No se pudo obtener el token JWT del contexto de seguridad");
	            }
	            MonedaResponse response = webClient.get()
	                .uri("/api/monedas/{numCia}/{claveMoneda}", numCia, claveMoneda)
	                .header("Authorization", "Bearer " + token)
	                .retrieve()
	                .bodyToMono(MonedaResponse.class)
	                .block();
	            return Optional.ofNullable(response);
	        } catch (WebClientResponseException.NotFound e) {
	            return Optional.empty();
	        } catch (Exception e) {
	            throw new RuntimeException("Error al consultar la moneda: " + e.getMessage(), e);
	        }
	    }
	 
	 
	 
	 
}