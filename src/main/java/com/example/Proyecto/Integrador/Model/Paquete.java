package com.example.Proyecto.Integrador.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
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

    public String asignarTipoPaquete(Double peso) {
        if (peso < 2.0) {
            return "LIVIANO";
        } else if (peso > 2.0 && peso < 5.0) {
            return "MEDIANO";
        }
        return "GRANDE";
    }


}
