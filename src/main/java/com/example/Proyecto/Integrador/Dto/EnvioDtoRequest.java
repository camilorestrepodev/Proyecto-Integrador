package com.example.Proyecto.Integrador.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioDtoRequest {
    private Integer numGuia;
    private String estadoEnvio;

    public EnvioDtoRequest() {
    }

    public EnvioDtoRequest(Integer numGuia, String estadoEnvio) {
        this.numGuia = numGuia;
        this.estadoEnvio = estadoEnvio;
    }
}
