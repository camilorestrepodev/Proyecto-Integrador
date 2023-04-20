package com.example.Proyecto.Integrador.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Long celular;
    private String correoElectronico;
    private String direccion;
    private String ciudad;

    public Persona(Integer cedula, String nombre, String apellido, Long celular, String correoElectronico, String direccion, String ciudad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }
    public Persona() {
    }

}
