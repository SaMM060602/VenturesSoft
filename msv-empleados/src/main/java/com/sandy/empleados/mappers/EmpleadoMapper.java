package com.sandy.empleados.mappers;

import com.sandy.empleados.dto.EmpleadoRequest;
import com.sandy.empleados.dto.EmpleadoResponse;
import com.sandy.empleados.entities.Empleado;


public class EmpleadoMapper {
	
	public static Empleado toEntity(EmpleadoRequest req) {
		Empleado e = new Empleado();
		e.setNumCia(req.numCia());
		e.setNumEmp(req.numEmp());
		e.setClaveMoneda(req.claveMoneda()); 
		e.setNombre(req.nombre());
		e.setApellidoPaterno(req.apellidoPaterno());
		e.setApellidoMaterno(req.apellidoMaterno());
		e.setPuesto(req.puesto());
		return e;
	}

	public static void updateEntity(Empleado entity, EmpleadoRequest req) {
		if (req.claveMoneda() != null)
		    entity.setClaveMoneda(req.claveMoneda());
		if (req.nombre() != null)
			entity.setNombre(req.nombre());
		if (req.apellidoMaterno() != null)
			entity.setApellidoPaterno(req.apellidoPaterno());
		if (req.apellidoMaterno() != null)
			entity.setApellidoMaterno(req.apellidoMaterno());
		if (req.puesto() != null)
			entity.setPuesto(req.puesto());

	}

	public static EmpleadoResponse toResponse(Empleado e) {
        return new EmpleadoResponse(
            e.getNumCia(),
            e.getNumEmp(),
            e.getClaveMoneda(),
            e.getNombre(),
            e.getApellidoPaterno(),
            e.getApellidoMaterno(),
            e.getPuesto()
        );

	}


}
