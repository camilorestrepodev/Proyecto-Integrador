package com.example.Proyecto.Integrador.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioDtoUpdate {
    private Integer cedula;
    private Integer numGuia;
    private String estadoEnvio;

    public EnvioDtoUpdate() {
    }

    public EnvioDtoUpdate(Integer cedula, Integer numGuia, String estadoEnvio) {
        this.cedula = cedula;
        this.numGuia = numGuia;
        this.estadoEnvio = estadoEnvio;
    }
}
