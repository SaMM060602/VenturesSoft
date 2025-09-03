package com.sandy.monedas.security;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sandy.monedas.exceptions.EntidadRelacionadaException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class LocalExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(LocalExceptionHandler.class.getName());

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.log(Level.WARNING, "VIOLACION DE RESTRICCION: " + e.getMessage());
        return ResponseEntity.badRequest().body(Map.of(
                "code", HttpStatus.BAD_REQUEST.value(),
                "response", "VIOLACION DE RESTRICCION: " + e.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.log(Level.WARNING, "ERROR DE VALIDACION: " + e.getMessage());
        String mensaje = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("ERROR DE VALIDACION EN LOS DATOS ENVIADOS");
        return ResponseEntity.badRequest().body(Map.of(
                "code", HttpStatus.BAD_REQUEST.value(),
                "response", mensaje
        ));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException e) {
        LOGGER.log(Level.WARNING, "NO SE ENCONTRO INFORMACION ASOCIADA CON EL IDENTIFICADOR INGRESADO");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "code", HttpStatus.NOT_FOUND.value(),
                "response", "NO SE ENCONTRO INFORMACION ASOCIADA CON EL IDENTIFICADOR INGRESADO"
        ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        LOGGER.log(Level.SEVERE, "ERROR EN LA INTEGRIDAD DE LOS DATOS: " + e.getMessage());
        return ResponseEntity.badRequest().body(Map.of(
                "code", HttpStatus.BAD_REQUEST.value(),
                "response", "ERROR EN LA INTEGRIDAD DE LOS DATOS: " + e.getMessage()
        ));
    }

    @ExceptionHandler(EntidadRelacionadaException.class)
    public ResponseEntity<Map<String, Object>> entidadRelacionadaException(EntidadRelacionadaException e) {
        LOGGER.log(Level.WARNING, "Error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "code", HttpStatus.NOT_FOUND.value(),
                "response", e.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception e) {
        LOGGER.log(Level.SEVERE, "ERROR INTERNO DEL SERVIDOR: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "code", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "response", "ERROR INTERNO DEL SERVIDOR: " + e.getMessage()
        ));
    }
}