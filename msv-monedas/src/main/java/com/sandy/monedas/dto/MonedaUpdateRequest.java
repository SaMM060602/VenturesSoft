package com.sandy.monedas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MonedaUpdateRequest(
		@NotBlank(message = "LA DESCRIPCIÓN ES REQUERIDA")
		@Size(min = 1, max = 100, message = "LA DESCRIPCIÓN DEBE TENER MAXIMO 100 CARACTERES")
		String descripcion,
		
		@NotBlank(message = "EL SIMBOLO ES REQUERIDA")
		@Size(min = 1, max = 2, message = "EL SIMBOLO DEBE TENER MAXIMO 2 CARACTERES")
		String simbolo,
		
		@NotBlank(message = "EL ESTATUS ES REQUERIDA") 
		@Size(min = 1, max = 15, message = "EL ESTATUS DEBE TENER MAXIMO 15 CARACTERES")
		String estatus
) {}