package com.example.Proyecto.Integrador.Controller;

import com.example.Proyecto.Integrador.Model.Cliente;
import com.example.Proyecto.Integrador.Model.Empleado;
import com.example.Proyecto.Integrador.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class EmpleadoController {

    public EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/empleado")
    public Empleado crearEmpleado (@RequestBody Empleado empleado){
        return this.empleadoService.crearEmpleado(empleado);
    }

    @PutMapping("/empleado")
    public Empleado actualizarEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.actualizarEmpleado(empleado);
    }

    @DeleteMapping("/empleado/{cedula}")
    public String eliminarEmpleado(@PathVariable Integer cedula) {
        this.empleadoService.eliminarEmpleado(cedula);
        return "El empleado con cedula " + cedula + " fue eliminado con exito";
    }

    @GetMapping("/empleado/{cedula}")
    public Optional<Empleado> obtenerEmpleadoCedula(@PathVariable Integer cedula){
        return this.empleadoService.obtenerEmpleadoCedula(cedula);
    }
}
