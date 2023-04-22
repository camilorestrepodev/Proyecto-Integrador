package com.example.Proyecto.Integrador.Controller;

import com.example.Proyecto.Integrador.Model.Empleado;
import com.example.Proyecto.Integrador.Model.ErrorResponse;
import com.example.Proyecto.Integrador.Service.EmpleadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@Api(value = "Empleados", description = "Controlador para gestionar empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/empleado")
    @ApiOperation(value = "Crear un empleado", response = Empleado.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Empleado creado exitosamente",response = Empleado.class),
            @ApiResponse(code = 400, message = "Petición inválida",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error interno del servidor",response = ErrorResponse.class)
    })
    @PreAuthorize("hasRole('WRITE')")
    public Empleado crearEmpleado (@RequestBody Empleado empleado){
        return this.empleadoService.crearEmpleado(empleado);
    }

    @PutMapping("/empleado")
    @ApiOperation(value = "Actualizar un empleado", response = Empleado.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado actualizado exitosamente",response = Empleado.class),
            @ApiResponse(code = 404, message = "Empleado no encontrado",response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Petición inválida",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error interno del servidor",response = ErrorResponse.class)
    })
    @PreAuthorize("hasRole('WRITE')")
    public Empleado actualizarEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.actualizarEmpleado(empleado);
    }

    @DeleteMapping("/empleado/{cedula}")
    @ApiOperation(value = "Eliminar un empleado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Empleado eliminado exitosamente",response = Empleado.class),
            @ApiResponse(code = 404, message = "Empleado no encontrado",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error interno del servidor",response = ErrorResponse.class)
    })
    @PreAuthorize("hasRole('WRITE')")
    public String eliminarEmpleado(@PathVariable Integer cedula) {
        this.empleadoService.eliminarEmpleado(cedula);
        return "El empleado con cedula " + cedula + " fue eliminado con exito";
    }

    @GetMapping("/empleado/{cedula}")
    @ApiOperation(value = "Obtener empleado por cédula", response = Empleado.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empleado encontrado", response = Empleado.class),
            @ApiResponse(code = 404, message = "Empleado no encontrado",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error interno del servidor",response = ErrorResponse.class)
    })
    @PreAuthorize("hasRole('READ')")
    public Optional<Empleado> obtenerEmpleadoCedula(@PathVariable Integer cedula){
        return this.empleadoService.obtenerEmpleadoCedula(cedula);
    }
}
