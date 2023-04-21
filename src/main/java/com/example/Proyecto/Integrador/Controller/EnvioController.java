package com.example.Proyecto.Integrador.Controller;

import com.example.Proyecto.Integrador.Dto.EnvioDto;
import com.example.Proyecto.Integrador.Dto.EnvioDtoRequest;
import com.example.Proyecto.Integrador.Model.Envio;
import com.example.Proyecto.Integrador.Service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EnvioController {
    private final EnvioService envioService;

    @Autowired
    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }


    @PostMapping("/envio")
    @PreAuthorize("hasRole('WRITE')")
    public EnvioDtoRequest crearEnvio(@RequestBody EnvioDto envio){
        return this.envioService.crearEnvio(envio);
    }

    @GetMapping("/envio/{numGuia}")
    @PreAuthorize("hasRole('READ')")
    public EnvioDto obtenerNumeroGuia(@PathVariable Integer numGuia){
        return this.envioService.obtenerNumeroGuia(numGuia);
    }

    @PutMapping("/envio")
    @PreAuthorize("hasRole('WRITE')")
    public EnvioDto actualizarEstadoPaquete(@RequestBody EnvioDto envio){
        return this.envioService.actualizarEstadoPaquete(envio);
    }

    @GetMapping("/envio")
    @PreAuthorize("hasRole('READ')")
    public List<Envio> filtrarPorEstado(@RequestParam ("cedula") Integer cedula, @RequestParam ("estadoEnvio") String estadoEnvio){
        return this.envioService.filtrarPorEstado(estadoEnvio,cedula);
    }

}
