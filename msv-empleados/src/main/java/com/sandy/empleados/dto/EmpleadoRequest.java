package com.sandy.empleados.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
public record EmpleadoRequest(
		@NotNull(message = "EL NUMERO DE COMPANIA ES REQUERID0")
		@Positive(message = "EL NUMERO DE COMPANIA DEBE TENER UN VALOR POSITIVO.")
		Long numCia,

		@NotNull(message = "EL NÚMERO DEL EMPLEADO ES REQUERIDO")
		@Min(value = 1, message = "EL NÚMERO DEL EMPLEADO DEBE SER MAYOR O IGUAL A 1")
		@Max(value = 999, message = "EL NÚMERO DEL EMPLEADO DEBE SER MÁXIMO DE 3 DÍGITOS")
		Integer numEmp,
		
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
	    @Size(min = 1, max = 50, message = "EL APELLIDO PATERNO DEBE TENER MÁXIMO 50 CARACTERES")
	    String apellidoMaterno,

	    @NotBlank(message = "EL PUESTO ES REQUERIDO")
	    @Size(min = 1, max = 50, message = "EL PUESTO DEBE TENER MÁXIMO 50 CARACTERES")
	    String puesto

		) {
	

}
