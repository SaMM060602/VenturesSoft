package com.sandy.oauth.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.oauth.dto.LoginRequest;
import com.sandy.oauth.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	private AuthService authService;

	public AuthController(AuthService authService) {
		
		this.authService = authService;
	}
	@Operation(summary = "Login", description = "Autentica a un usuario y devuelve un token JWT.", security = {})
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request)throws Exception{
		String token = authService.authenticate(request.username(), request.password());
		
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		return ResponseEntity.ok(response);
		
	}
	
}
