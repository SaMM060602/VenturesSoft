package com.sandy.empleados.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NombreRequest(
		@NotBlank(message = "EL NOMBRE ES REQUERIDO")
	    @Size(min = 1, max = 50, message = "EL NOMBRE DEBE TENER MÁXIMO 50 CARACTERES")
	    String nombre
	    ) {
	

}
