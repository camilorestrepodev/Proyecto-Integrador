package com.example.Proyecto.Integrador.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate antiguedad;
    private String tipoSangre;
    private String tipoEmpleado;

    public Empleado() {
    }

    public Empleado(Integer cedula, String nombre, String apellido, Long celular, String correoElectronico, String direccion, String ciudad) {
        super(cedula, nombre, apellido, celular, correoElectronico, direccion, ciudad);
    }

    public LocalDate getAntiguedad() {
        return antiguedad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }
}


