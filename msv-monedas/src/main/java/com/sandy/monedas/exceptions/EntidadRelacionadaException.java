package com.sandy.monedas.exceptions;

public class EntidadRelacionadaException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;

	public EntidadRelacionadaException(String mensaje) {
		super(mensaje);
	}

}
