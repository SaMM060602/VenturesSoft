package com.sandy.monedas.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MonedaRequest(
		
		@NotNull(message = "EL NUMERO DE COMPANIA ES REQUERID0")
		@Positive(message = "EL NUMERO DE COMPANIA DEBE TENER UN VALOR POSITIVO.")
		Long numCia,
		
		@NotBlank(message = "LA CLAVE DE LA MONEDA ES REQUERIDA")
		@Size(min = 1, max = 3, message = "LA CLAVE DE LA MONEDA DEBE TENER MAXIMO 3 CARACTERES")
		String claveMoneda,
	
		@NotBlank(message = "LA DESCRIPCIÓN ES REQUERIDA")
		@Size(min = 1, max = 30, message = "LA DESCRIPCIÓN DEBE TENER MAXIMO 50 CARACTERES")
		String descripcion,
		
		@NotBlank(message = "EL SIMBOLO ES REQUERIDA")
		@Size(min = 1, max = 2, message = "EL SIMBOLO DEBE TENER MAXIMO 2 CARACTERES")
		String simbolo,
		
		@NotBlank(message = "EL ESTATUS ES REQUERIDA") 
		@Size(min = 1, max = 15, message = "EL ESTATUS DEBE TENER MAXIMO 15 CARACTERES")
		String estatus
		
	) {

}
