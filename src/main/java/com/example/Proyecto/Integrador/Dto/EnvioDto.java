package com.example.Proyecto.Integrador.Dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class EnvioDto implements Serializable {
    private Integer cedula;
    private String nombreRemitente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombrePersona;
    private Integer numeroPersona;
    private Double peso;
    private String estadoEnvio;
    private Integer valorDeclarado;
    private Integer valorEnvio;

    public EnvioDto() {
    }

    public EnvioDto(Integer cedula, String nombreRemitente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombrePersona, Integer numeroPersona, Double peso, String estadoEnvio, Integer valorDeclarado) {
        this.cedula = cedula;
        this.nombreRemitente = nombreRemitente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombrePersona = nombrePersona;
        this.numeroPersona = numeroPersona;
        this.peso = peso;
        this.estadoEnvio = estadoEnvio;
        this.valorDeclarado = valorDeclarado;
    }


}
