package com.sandy.empleados.entities;

import jakarta.persistence.*;

@Entity
@IdClass(EmpleadoId.class)
@Table(name = "HU_EMPLS")
public class Empleado {

	@Id
	@Column(name = "NUM_CIA", nullable = false)
	private Long numCia;

	@Id
	@Column(name = "NUM_EMP", nullable = false)
	private Integer numEmp;

	@Column(name = "CLAVE_MONEDA", nullable = false)
	private String claveMoneda;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;

	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;

	@Column(name = "PUESTO")
	private String puesto;

	public Long getNumCia() {
		return numCia;
	}

	public void setNumCia(Long numCia) {
		this.numCia = numCia;
	}

	public Integer getNumEmp() {
		return numEmp;
	}

	public void setNumEmp(Integer numEmp) {
		this.numEmp = numEmp;
	}

	public String getClaveMoneda() {
		return claveMoneda;
	}

	public void setClaveMoneda(String claveMoneda) {
		this.claveMoneda = claveMoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

}
