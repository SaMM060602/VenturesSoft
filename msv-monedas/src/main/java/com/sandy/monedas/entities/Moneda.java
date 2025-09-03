package com.sandy.monedas.entities;

import jakarta.persistence.*;

@Entity
@IdClass(MonedaId.class)
@Table(name = "HU_CAT_MONEDA")
public class Moneda {

    @Id
    @Column(name = "NUM_CIA", nullable = false)
    private Long numCia;

    @Id
    @Column(name = "CLAVE_MONEDA", nullable = false)
    private String claveMoneda;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "SIMBOLO")
    private String simbolo;

    @Column(name = "ESTATUS")
    private String estatus;

    // Getters y Setters
    public Long getNumCia() { return numCia; }
    public void setNumCia(Long numCia) { this.numCia = numCia; }

    public String getClaveMoneda() { return claveMoneda; }
    public void setClaveMoneda(String claveMoneda) { this.claveMoneda = claveMoneda; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getSimbolo() { return simbolo; }
    public void setSimbolo(String simbolo) { this.simbolo = simbolo; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }
}
