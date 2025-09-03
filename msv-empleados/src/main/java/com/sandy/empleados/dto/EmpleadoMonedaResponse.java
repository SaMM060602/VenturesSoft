package com.sandy.empleados.dto;

public record EmpleadoMonedaResponse(
		Long numCia,
	    Integer numEmp,
	    String claveMoneda,
	    String nombre,
	    String apellidoPaterno,
	    String apellidoMaterno,
	    String puesto,
	    // Atributos de la moneda
	    String descripcionMoneda,
	    String simboloMoneda
		) {

}