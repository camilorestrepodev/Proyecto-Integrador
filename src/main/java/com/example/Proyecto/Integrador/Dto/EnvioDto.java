package com.example.Proyecto.Integrador.Dto;


import java.io.Serializable;

public class EnvioDto implements Serializable {
    private Integer numGuia;
    private Integer cedula;
    private String nombreRemitente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombrePersona;
    private Integer numeroPersona;
    private Double peso;
    private Integer valorDeclarado;
    private String estadoEnvio;
    private Integer valorEnvio;

    public EnvioDto() {
    }

    public EnvioDto(Integer numGuia) {
        this.numGuia = numGuia;
    }

    public EnvioDto( Integer cedula, String nombreRemitente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombrePersona, Integer numeroPersona, Double peso, Integer valorDeclarado) {;
        this.cedula = cedula;
        this.nombreRemitente = nombreRemitente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombrePersona = nombrePersona;
        this.numeroPersona = numeroPersona;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public Integer getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(Integer numGuia) {
        this.numGuia = numGuia;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Integer getNumeroPersona() {
        return numeroPersona;
    }

    public void setNumeroPersona(Integer numeroPersona) {
        this.numeroPersona = numeroPersona;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(Integer valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Integer getValorEnvio() {
        return valorEnvio;
    }

    public void setValorEnvio(Integer valorEnvio) {
        this.valorEnvio = valorEnvio;
    }
}
