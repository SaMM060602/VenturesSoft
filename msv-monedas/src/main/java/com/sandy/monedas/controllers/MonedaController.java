package com.sandy.monedas.controllers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sandy.monedas.services.MonedaService;

import org.springframework.web.bind.annotation.RequestBody;


import com.sandy.monedas.dto.MonedaRequest;
import com.sandy.monedas.dto.MonedaResponse;
import com.sandy.monedas.dto.MonedaUpdateRequest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/monedas")
public class MonedaController {

    private final MonedaService service;

    public MonedaController(MonedaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{numCia:\\d+}/{claveMoneda}")
    public ResponseEntity<?> obtenerClave(@PathVariable String claveMoneda, @PathVariable Long numCia) {
        return ResponseEntity.ok(service.obtener(claveMoneda, numCia));
    }

    @PostMapping
    public ResponseEntity<MonedaResponse> crear(@Valid @RequestBody MonedaRequest request) {
        MonedaResponse response = service.crear(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{numCia:\\d+}/{claveMoneda}")
    public ResponseEntity<MonedaResponse> actualizar(@PathVariable String claveMoneda,@PathVariable Long numCia, @Valid @RequestBody MonedaUpdateRequest request) {
        return ResponseEntity.ok(service.actualizar(claveMoneda, numCia,request));
    }

    @DeleteMapping("/{numCia:\\d+}/{claveMoneda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String claveMoneda, @PathVariable Long numCia) {
        service.eliminar(claveMoneda, numCia);
    }
}