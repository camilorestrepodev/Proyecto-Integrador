package com.example.Proyecto.Integrador.Dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class EnvioDto implements Serializable {
    @ApiModelProperty(value = "La cédula del remitente", example = "1234567890", required = true)
    private Integer cedula;

    @ApiModelProperty(value = "El nombre del remitente", example = "Juan Pérez", required = true)
    private String nombreRemitente;

    @ApiModelProperty(value = "La ciudad de origen del envío", example = "Bogotá", required = true)
    private String ciudadOrigen;

    @ApiModelProperty(value = "La ciudad de destino del envío", example = "Medellín", required = true)
    private String ciudadDestino;

    @ApiModelProperty(value = "La dirección de destino del envío", example = "Carrera 5 #25-28", required = true)
    private String direccionDestino;

    @ApiModelProperty(value = "El nombre de la persona que recibe el envío", example = "María Pérez", required = true)
    private String nombrePersona;

    @ApiModelProperty(value = "El número de teléfono de la persona que recibe el envío", example = "3214567890", required = true)
    private Integer numeroPersona;

    @ApiModelProperty(value = "El peso del envío en kilogramos", example = "2.5", required = true)
    private Double peso;

    @ApiModelProperty(value = "El estado del envío", example = "En tránsito", required = true)
    private String estadoEnvio;

    @ApiModelProperty(value = "El valor declarado del envío", example = "100000", required = true)
    private Integer valorDeclarado;

    @ApiModelProperty(value = "El valor del envío", example = "50000", required = true)
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
