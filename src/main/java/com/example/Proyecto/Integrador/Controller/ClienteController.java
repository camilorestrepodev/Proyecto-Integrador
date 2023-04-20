package com.example.Proyecto.Integrador.Controller;

import com.example.Proyecto.Integrador.Model.Cliente;
import com.example.Proyecto.Integrador.Service.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    public Cliente crearCliente (@RequestBody Cliente cliente){
        return this.clienteService.crearCliente(cliente);
    }

    @PutMapping("/cliente")
    public Cliente actualizarEmpleado(@RequestBody Cliente cliente) {
        return clienteService.actualizarCliente(cliente);
    }
    @DeleteMapping("/cliente/{cedula}")
    public String eliminarCliente(@PathVariable Integer cedula) {
        this.clienteService.eliminarCliente(cedula);
        return "El cliente con cedula " + cedula + " fue eliminado con exito";
    }
    @GetMapping("/cliente/{cedula}")
    public Optional<Cliente> obtenerClienteCedula(@PathVariable Integer cedula){
        return this.clienteService.obtenerClienteCedula(cedula);
    }
}
