package com.sandy.empleados.services;

import com.sandy.empleados.dto.EmpleadoMonedaResponse;
import com.sandy.empleados.dto.EmpleadoRequest;
import com.sandy.empleados.dto.EmpleadoResponse;
import com.sandy.empleados.dto.EmpleadoUpdateRequest;
import com.sandy.empleados.entities.Empleado;
import com.sandy.empleados.exceptions.EntidadRelacionadaException;
import com.sandy.empleados.mappers.EmpleadoMapper;
import com.sandy.empleados.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import com.sandy.empleados.clients.MonedaClient;
import com.sandy.empleados.dto.MonedaResponse;
import com.sandy.empleados.dto.NombreRequest;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {
	private final EmpleadoRepository repository;
	private final MonedaClient monedaClient;

	// Constructor
	public EmpleadoServiceImpl(EmpleadoRepository repository, MonedaClient monedaClient) {
		this.repository = repository;
		this.monedaClient = monedaClient;
	}

	@Override
	@Transactional(readOnly = true)
	public List listar() {
		return repository.findAll().stream().map(EmpleadoMapper::toResponse).toList();
	}

	@Override
	public EmpleadoResponse obtener(Integer numEmp, Long numCia) {
		Empleado e = repository.findByNumCiaAndNumEmp(numCia, numEmp)
				.orElseThrow(() -> new EntidadRelacionadaException("Empleado no encontrado"));
		return EmpleadoMapper.toResponse(e);
	}

	@Override
	public EmpleadoResponse crear(EmpleadoRequest req) {
		// Valida que la compañía exista
		if (!monedaClient.existeClave(req.numCia(), req.claveMoneda())) {
			throw new EntidadRelacionadaException("La compañía no existe en el sistema.");
		}

		// Validar que la clave de moneda exista
		if (!monedaClient.existeClave(req.numCia(), req.claveMoneda())) {
			throw new EntidadRelacionadaException("La clave de moneda no existe en el sistema.");
		}

		if (repository.existsByNumCiaAndNumEmp(req.numCia(), req.numEmp())) {
			throw new EntidadRelacionadaException("El número del Empleado ya existe para la compañía.");
		}

		Empleado empleado = EmpleadoMapper.toEntity(req);
		repository.save(empleado);
		return EmpleadoMapper.toResponse(empleado);
	}

	@Override
	public EmpleadoResponse actualizar(Integer numEmp, Long numCia, EmpleadoUpdateRequest request) {

		// Valida que la compañía exista
		if (!monedaClient.existeClave(request.numCia(), request.claveMoneda())) {
			throw new EntidadRelacionadaException("La compañía no existe en el sistema.");
		}

		// Validar que la clave de moneda exista
		if (!monedaClient.existeClave(request.numCia(), request.claveMoneda())) {
			throw new EntidadRelacionadaException("La clave de moneda no existe en el sistema.");
		}
		// Buscar el empleado por numCia y numEmp
		Empleado empleado = repository.findByNumCiaAndNumEmp(numCia, numEmp)
				.orElseThrow(() -> new EntidadRelacionadaException("Empleado no encontrado"));

		empleado.setNombre(request.nombre());
		empleado.setClaveMoneda(request.claveMoneda());
		empleado.setApellidoPaterno(request.apellidoPaterno());
		empleado.setApellidoMaterno(request.apellidoMaterno());
		empleado.setPuesto(request.puesto());

		repository.save(empleado);
		return EmpleadoMapper.toResponse(empleado);
	}

	@Override
	public void eliminar(Integer numEmp, Long numCia) {
		if (!repository.existsByNumCiaAndNumEmp(numCia, numEmp))
			throw new EntidadRelacionadaException("Empleado no encontrado");
		repository.deleteByNumCiaAndNumEmp(numCia, numEmp);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpleadoMonedaResponse obtenerEmpleadoConMoneda(Long numCia, Integer numEmp) {
        Empleado empleado = repository.findByNumCiaAndNumEmp(numCia, numEmp)
                .orElseThrow(() -> new EntidadRelacionadaException("Empleado no encontrado."));

        MonedaResponse moneda = monedaClient.existeMoneda(numCia, empleado.getClaveMoneda())
                .orElseThrow(() -> new EntidadRelacionadaException("Moneda no encontrada para el empleado."));

        return new EmpleadoMonedaResponse(
            empleado.getNumCia(),
            empleado.getNumEmp(),
            empleado.getClaveMoneda(),
            empleado.getNombre(),
            empleado.getApellidoPaterno(),
            empleado.getApellidoMaterno(),
            empleado.getPuesto(),
            moneda.descripcion(),
            moneda.simbolo()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoMonedaResponse> obtenerEmpleadosPorMoneda(Long numCia, String claveMoneda) {
        // Valida que la clave de moneda exista antes de buscar
        Optional<MonedaResponse> monedaOpt = monedaClient.existeMonedaEmp(numCia, claveMoneda);
        if (monedaOpt.isEmpty()) {
            throw new EntidadRelacionadaException("La clave de moneda '" + claveMoneda + "' no existe en el microservicio de monedas.");
        }
        List<Empleado> empleados = repository.findByNumCiaAndClaveMoneda(numCia, claveMoneda);
        if (empleados.isEmpty()) {
            return List.of();
        }
        MonedaResponse moneda = monedaOpt.get();
        return empleados.stream()
            .map(empleado -> new EmpleadoMonedaResponse(
                empleado.getNumCia(),
                empleado.getNumEmp(),
                empleado.getClaveMoneda(),
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno(),
                empleado.getPuesto(),
                moneda.descripcion(),
                moneda.simbolo()
            ))
            .collect(Collectors.toList());
    }


	@Override
	public List<EmpleadoMonedaResponse> obtenerEmpleadosPorNombre(Long numCia, NombreRequest request) {
        List<Empleado> empleados = repository.findByNumCiaAndNombreContainingIgnoreCase(numCia, request.nombre());
        if (empleados.isEmpty()) {
            return List.of();
        }
        return empleados.stream().map(empleado -> {
            MonedaResponse moneda = monedaClient.existeMoneda(numCia, empleado.getClaveMoneda())
                .orElseThrow(() -> new EntidadRelacionadaException("Moneda no encontrada para el empleado."));
            return new EmpleadoMonedaResponse(
                empleado.getNumCia(),
                empleado.getNumEmp(),
                empleado.getClaveMoneda(),
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno(),
                empleado.getPuesto(),
                moneda.descripcion(),
                moneda.simbolo()
            );
        }).collect(Collectors.toList());
    }

	@Override
	public List<EmpleadoMonedaResponse> obtenerEmpleadosPorNombre(NombreRequest request) {
        List<Empleado> empleados = repository.findByNombreContainingIgnoreCase(request.nombre());
        if (empleados.isEmpty()) {
            return List.of();
        }
        return empleados.stream().map(empleado -> {
            MonedaResponse moneda = monedaClient.existeMoneda(empleado.getNumCia(), empleado.getClaveMoneda())
                .orElseThrow(() -> new EntidadRelacionadaException("Moneda no encontrada para el empleado."));
            return new EmpleadoMonedaResponse(
                empleado.getNumCia(),
                empleado.getNumEmp(),
                empleado.getClaveMoneda(),
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno(),
                empleado.getPuesto(),
                moneda.descripcion(),
                moneda.simbolo()
            );
        }).collect(java.util.stream.Collectors.toList());
    }

}