package com.sandy.empleados.entities;
import java.io.Serializable;
import java.util.Objects;

public class EmpleadoId implements Serializable {
	
	private Long numCia;
	private Integer numEmp;

	public EmpleadoId() {}

	public EmpleadoId(Long numCia, Integer numEmp) {
		this.numCia = numCia;
		this.numEmp = numEmp;
	}

	public Long getNumCia() { return numCia; }
	public void setNumCia(Long numCia) { this.numCia = numCia; }

	public Integer getNumEmp() { return numEmp; }
	public void setNumEmp(Integer numEmp) { this.numEmp = numEmp; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EmpleadoId)) return false;
		EmpleadoId that = (EmpleadoId) o;
		return Objects.equals(numCia, that.numCia) &&
			   Objects.equals(numEmp, that.numEmp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numCia, numEmp);
	}

}
