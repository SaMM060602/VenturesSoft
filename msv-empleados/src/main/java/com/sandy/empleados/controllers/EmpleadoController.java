package com.sandy.empleados.controllers;

import com.sandy.empleados.dto.EmpleadoMonedaResponse;
import com.sandy.empleados.dto.EmpleadoRequest;
import com.sandy.empleados.dto.EmpleadoResponse;
import com.sandy.empleados.dto.EmpleadoUpdateRequest;
import com.sandy.empleados.dto.NombreRequest;
import com.sandy.empleados.services.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{numCia:\\d+}/{numEmp}")
    public ResponseEntity<?> obtenerClave(@PathVariable Integer numEmp, @PathVariable Long numCia) {
        return ResponseEntity.ok(service.obtener(numEmp, numCia));
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crear(@Valid @RequestBody EmpleadoRequest request) {
        EmpleadoResponse response = service.crear(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{numCia:\\d+}/{numEmp}")
    public ResponseEntity<EmpleadoResponse> actualizar(@PathVariable Integer numEmp, @PathVariable Long numCia, @Valid @RequestBody EmpleadoUpdateRequest request) {
        return ResponseEntity.ok(service.actualizar(numEmp, numCia, request));
    }

    @DeleteMapping("/{numCia:\\d+}/{numEmp}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer numEmp, @PathVariable Long numCia) {
        service.eliminar(numEmp, numCia);
    }

    @GetMapping("/con-moneda/{numCia}/{numEmp}")
    public ResponseEntity<EmpleadoMonedaResponse> obtenerEmpleadoConMoneda(@PathVariable Long numCia, @PathVariable Integer numEmp) {
        return ResponseEntity.ok(service.obtenerEmpleadoConMoneda(numCia, numEmp));
    }

    @GetMapping("/por-moneda/{numCia}/{claveMoneda}")
    public ResponseEntity<List<EmpleadoMonedaResponse>> obtenerEmpleadosPorMoneda(@PathVariable Long numCia, @PathVariable String claveMoneda) {
        return ResponseEntity.ok(service.obtenerEmpleadosPorMoneda(numCia, claveMoneda));
    }

    @PostMapping("/por-nombre/{numCia}")
    public ResponseEntity<List<EmpleadoMonedaResponse>> buscarPorNombre(@PathVariable Long numCia, @Valid @RequestBody NombreRequest nombreRequest) {
        List<EmpleadoMonedaResponse> empleados = service.obtenerEmpleadosPorNombre(numCia, nombreRequest);
        return ResponseEntity.ok(empleados);
    }

    @PostMapping("/por-nombre")
    public ResponseEntity<List<EmpleadoMonedaResponse>> buscarPorNombreSinNumCia(@Valid @RequestBody NombreRequest nombreRequest) {
        List<EmpleadoMonedaResponse> empleados = service.obtenerEmpleadosPorNombre(nombreRequest);
        return ResponseEntity.ok(empleados);
    }
}