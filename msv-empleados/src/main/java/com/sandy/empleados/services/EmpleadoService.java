package com.sandy.empleados.services;

import java.util.List;

import com.sandy.empleados.dto.EmpleadoMonedaResponse;
import com.sandy.empleados.dto.EmpleadoRequest;
import com.sandy.empleados.dto.EmpleadoResponse;
import com.sandy.empleados.dto.EmpleadoUpdateRequest;

public interface EmpleadoService {

	List listar();

	EmpleadoResponse obtener(Integer numEmp, Long numCia);

	EmpleadoResponse crear(EmpleadoRequest request);

	EmpleadoResponse actualizar(Integer numEmp, Long numCia, EmpleadoUpdateRequest request);

	void eliminar(Integer numEmp, Long numCia);
	
	EmpleadoMonedaResponse obtenerEmpleadoConMoneda(Long numCia, Integer numEmp);
	
    List<EmpleadoMonedaResponse> obtenerEmpleadosPorMoneda(Long numCia, String claveMoneda);

}
