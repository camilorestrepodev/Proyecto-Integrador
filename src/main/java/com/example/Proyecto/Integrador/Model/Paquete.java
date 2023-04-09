package com.example.Proyecto.Integrador.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paquetes")
public class Paquete implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn
    private Integer idPaquete;
    private String tipoPaquete;
    private Double peso;
    private Integer valorDeclarado;

    public Paquete() {
    }

    public Paquete(String tipoPaquete, Double peso, Integer valorDeclarado) {
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public Double getPeso() {
        return peso;
    }

    public Integer getValorDeclarado() {
        return valorDeclarado;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }
}