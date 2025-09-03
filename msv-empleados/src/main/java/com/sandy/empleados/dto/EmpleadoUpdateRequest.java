package com.sandy.empleados.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EmpleadoUpdateRequest(
		@NotNull(message = "EL NUMERO DE COMPANIA ES REQUERID0")
		@Positive(message = "EL NUMERO DE COMPANIA DEBE TENER UN VALOR POSITIVO.")
		Long numCia,
		
		@NotBlank(message = "LA CLAVE DE LA MONEDA ES REQUERIDA")
	    @Size(min = 1, max = 3, message = "LA CLAVE DE LA MONEDA DEBE TENER MÁXIMO 3 CARACTERES")
	    String claveMoneda,
	    
		@NotBlank(message = "EL NOMBRE ES REQUERIDO")
	    @Size(min = 1, max = 50, message = "EL NOMBRE DEBE TENER MÁXIMO 50 CARACTERES")
	    String nombre,
	    
		@NotBlank(message = "EL APELLIDO PATERNO ES REQUERIDO")
	    @Size(min = 1, max = 50, message = "EL APELLIDO PATERNO DEBE TENER MÁXIMO 50 CARACTERES")
	    String apellidoPaterno,
	    
		@NotBlank(message = "EL APELLIDO MATERNO ES REQUERIDO")
	    @Size(min = 1, max = 50, message = "EL APELLIDO MATERNO DEBE TENER MÁXIMO 50 CARACTERES")
	    String apellidoMaterno,

	    @NotBlank(message = "EL PUESTO ES REQUERIDO")
	    @Size(min = 1, max = 50, message = "EL PUESTO DEBE TENER MÁXIMO 50 CARACTERES")
	    String puesto
		) {

}