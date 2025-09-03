package com.sandy.monedas.services;

import com.sandy.monedas.dto.MonedaRequest;
import com.sandy.monedas.dto.MonedaResponse;
import com.sandy.monedas.dto.MonedaUpdateRequest;
import com.sandy.monedas.entities.Moneda;
import com.sandy.monedas.exceptions.EntidadRelacionadaException;
import com.sandy.monedas.mappers.MonedaMapper;
import com.sandy.monedas.repositories.MonedaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MonedaServiceImpl implements MonedaService {

	private final MonedaRepository repository;

	public MonedaServiceImpl(MonedaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> listar() {
		return repository.findAll().stream().map(MonedaMapper::toResponse).toList();
	}

	@Override
	public MonedaResponse obtener(String claveMoneda, Long numCia) {
		Moneda m = repository.findByNumCiaAndClaveMoneda(numCia, claveMoneda)
				.orElseThrow(() -> new EntidadRelacionadaException("Moneda no encontrada"));
		return MonedaMapper.toResponse(m);
	}

	@Override
	public MonedaResponse crear(MonedaRequest req) {
		// Validar que no exista claveMoneda repetida en el mismo numCia
		if (repository.existsByNumCiaAndClaveMoneda(req.numCia(), req.claveMoneda())) {
			throw new EntidadRelacionadaException(
					"La clave de moneda ya existe para la compañía y no puede repetirse.");
		}
		Moneda moneda = MonedaMapper.toEntity(req);
		repository.save(moneda);
		return MonedaMapper.toResponse(moneda);
	}

	@Override
	public MonedaResponse actualizar(String claveMoneda, Long numCia, MonedaUpdateRequest req) {
		Moneda moneda = repository.findByNumCiaAndClaveMoneda(numCia, claveMoneda).orElseThrow(
				() -> new EntidadRelacionadaException("Moneda no encontrada para la compañía especificada."));

		// Actualizar solo los campos permitidos
		moneda.setDescripcion(req.descripcion());
		moneda.setSimbolo(req.simbolo());
		moneda.setEstatus(req.estatus());

		repository.save(moneda);
		return MonedaMapper.toResponse(moneda);
	}

	@Override
	public void eliminar(String claveMoneda, Long numCia) {

		if (!repository.existsByNumCiaAndClaveMoneda(numCia, claveMoneda))
			throw new EntidadRelacionadaException("Moneda no encontrada");
		repository.deleteByNumCiaAndClaveMoneda(numCia, claveMoneda);
	}

}