package com.sandy.empleados.dto;

public record EmpleadoResponse(
		 Long numCia,
		    Integer numEmp,
		    String claveMoneda,
		    String nombre,
		    String apellidoPaterno,
		    String apellidoMaterno,
		    String puesto
		) {

}
