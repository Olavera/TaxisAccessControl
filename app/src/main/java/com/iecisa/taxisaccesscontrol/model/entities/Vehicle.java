package com.iecisa.taxisaccesscontrol.model.entities;

/**
 * @author Olavera
 */
public class Vehicle {

    private long idListaNegra;
    private String matricula;
    private int estado;
    private String fechaInclusion;

    public Vehicle() {
    }

    public Vehicle(long idListaNegra, String matricula, int estado, String fechaInclusion) {
        this.idListaNegra = idListaNegra;
        this.matricula = matricula;
        this.estado = estado;
        this.fechaInclusion = fechaInclusion;
    }

    public long getIdListaNegra() {
        return idListaNegra;
    }

    public void setIdListaNegra(long idListaNegra) {
        this.idListaNegra = idListaNegra;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaInclusion() {
        return fechaInclusion;
    }

    public void setFechaInclusion(String fechaInclusion) {
        this.fechaInclusion = fechaInclusion;
    }
}
