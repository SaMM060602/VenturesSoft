package com.sandy.monedas.entities;

import java.io.Serializable;
import java.util.Objects;

public class MonedaId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long numCia;
    private String claveMoneda;

    public MonedaId() {}

    public MonedaId(Long numCia, String claveMoneda) {
        this.numCia = numCia;
        this.claveMoneda = claveMoneda;
    }

    public Long getNumCia() { return numCia; }
    public void setNumCia(Long numCia) { this.numCia = numCia; }

    public String getClaveMoneda() { return claveMoneda; }
    public void setClaveMoneda(String claveMoneda) { this.claveMoneda = claveMoneda; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonedaId)) return false;
        MonedaId that = (MonedaId) o;
        return Objects.equals(numCia, that.numCia) &&
               Objects.equals(claveMoneda, that.claveMoneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCia, claveMoneda);
    }
}
