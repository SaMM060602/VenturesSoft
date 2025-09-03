package com.sandy.monedas.services;

import java.util.List;


import com.sandy.monedas.dto.MonedaRequest;
import com.sandy.monedas.dto.MonedaResponse;
import com.sandy.monedas.dto.MonedaUpdateRequest;

public interface MonedaService {

	List<?> listar();

	MonedaResponse obtener(String claveMoneda, Long numCia);

	MonedaResponse crear(MonedaRequest request);

	MonedaResponse actualizar(String claveMoneda, Long numCia, MonedaUpdateRequest request);

	void eliminar(String claveMoneda, Long numCia);

}
