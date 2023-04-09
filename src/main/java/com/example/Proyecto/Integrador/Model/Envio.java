package com.example.Proyecto.Integrador.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter @Setter
@Entity
@Table(name = "envios")
public class Envio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numGuia;
    @JoinColumn
    @ManyToOne
    private Cliente cliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombrePersona;
    private Integer numeroPersona;
    private String horaEntrega;
    private String estadoEnvio;
    private Integer valorEnvio;
    @JoinColumn
    @OneToOne
    private Paquete paquete;

    public Envio() {
    }

    public Envio( Cliente cliente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombrePersona, Integer numeroPersona, String horaEntrega, String estadoEnvio, Integer valorEnvio, Paquete paquete) {
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombrePersona = nombrePersona;
        this.numeroPersona = numeroPersona;
        this.horaEntrega = horaEntrega;
        this.estadoEnvio = estadoEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }
}
