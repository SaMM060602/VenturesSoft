package com.sandy.empleados.services;
import java.io.Serializable;

public record MonedaResponse(
		  String claveMoneda,
		    String descripcion,
		    String simbolo
		) implements Serializable {

}