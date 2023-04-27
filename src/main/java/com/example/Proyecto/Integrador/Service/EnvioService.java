package com.example.Proyecto.Integrador.Service;

import com.example.Proyecto.Integrador.Dto.EnvioDto;
import com.example.Proyecto.Integrador.Dto.EnvioDtoRequest;
import com.example.Proyecto.Integrador.Dto.EnvioDtoUpdate;
import com.example.Proyecto.Integrador.Exception.ApiRequestException;
import com.example.Proyecto.Integrador.Model.Cliente;
import com.example.Proyecto.Integrador.Model.Empleado;
import com.example.Proyecto.Integrador.Model.Envio;
import com.example.Proyecto.Integrador.Model.Paquete;
import com.example.Proyecto.Integrador.Repository.ClienteRepository;
import com.example.Proyecto.Integrador.Repository.EmpleadoRepository;
import com.example.Proyecto.Integrador.Repository.EnvioRepository;
import com.example.Proyecto.Integrador.Repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {
    private final EnvioRepository envioRepository;
    private final ClienteRepository clienteRepository;
    private final PaqueteRepository paqueteRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EnvioService(EnvioRepository envioRepository, ClienteRepository clienteRepository, PaqueteRepository paqueteRepository, EmpleadoRepository empleadoRepository) {
        this.envioRepository = envioRepository;
        this.clienteRepository = clienteRepository;
        this.paqueteRepository = paqueteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    // Función para crear énvio
    public EnvioDtoRequest crearEnvio(EnvioDto envioDto) {
        if (envioDto.getCedula() == null ||
                envioDto.getNombreRemitente() == null ||
                envioDto.getCiudadOrigen() == null ||
                envioDto.getCiudadDestino() == null ||
                envioDto.getDireccionDestino() == null ||
                envioDto.getNombrePersona() == null ||
                envioDto.getNumeroPersona() == null ||
                envioDto.getPeso() == null ||
                envioDto.getValorDeclarado() == null) {
            throw new ApiRequestException("Algunos de los campos ingresados estan vacios");
        }

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(envioDto.getCedula());
        if (!clienteOptional.isPresent()) {
            throw new ApiRequestException("El cliente con cedula " + envioDto.getCedula() + " debe de estar registrado para poder enviar el paquete.");
        }

        Paquete paquete = new Paquete();
        paquete.setTipoPaquete(paquete.asignarTipoPaquete(envioDto.getPeso()));
        paquete.setPeso(envioDto.getPeso());
        paquete.setValorDeclarado(envioDto.getValorDeclarado());
        this.paqueteRepository.save(paquete);

        Envio envio = new Envio(
                clienteOptional.get(),
                envioDto.getCiudadOrigen(),
                envioDto.getCiudadDestino(),
                envioDto.getDireccionDestino(),
                envioDto.getNombrePersona(),
                envioDto.getNumeroPersona(),
                asignarHora(),
                "RECIBIDO",
                Envio.asignarPrecioEnvio(paquete.getTipoPaquete()),
                paquete
        );
        Envio envio1 = this.envioRepository.save(envio);
        envio1.setNumGuia(envio1.getNumGuia());
        EnvioDtoRequest envioDtoRequest = new EnvioDtoRequest(envio1.getNumGuia(),envio1.getEstadoEnvio());
        return envioDtoRequest;
    }


    //Función para asignar la hora
    public String asignarHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public EnvioDto obtenerNumeroGuia(Integer numGuia) {
        Optional<Envio> envio = this.envioRepository.findById(numGuia);

        if (!envio.isPresent()) {
            throw new ApiRequestException("El numero de guia no existe");
        }

        Envio envioData = envio.get();
        Cliente cliente = envioData.getCliente();
        Paquete paquete = envioData.getPaquete();

        String ciudadOrigen = envioData.getCiudadOrigen();
        String ciudadDestino = envioData.getCiudadDestino();
        String direccionDestino = envioData.getDireccionDestino();
        String nombrePersona = envioData.getNombrePersona();
        Integer celular = envioData.getNumeroPersona();
        Integer valorDeclarado = paquete.getValorDeclarado();
        Double peso = paquete.getPeso();
        Integer valorEnvio = envioData.getValorEnvio();
        String estadoEnvio = envioData.getEstadoEnvio();

        EnvioDto envioDto = new EnvioDto(
                cliente.getCedula(),
                cliente.getNombre(),
                ciudadOrigen,
                ciudadDestino,
                direccionDestino,
                nombrePersona,
                celular,
                peso,
                estadoEnvio,
                valorDeclarado
        );

        if (estadoEnvio.equals("RECIBIDO")) {
            envioData.setEstadoEnvio("EN RUTA");
            envioDto.setEstadoEnvio("EN RUTA");
            this.envioRepository.save(envioData);
        } else {
            envioDto.setEstadoEnvio(estadoEnvio);
        }

        envioDto.setValorEnvio(valorEnvio);

        return envioDto;
    }


    public EnvioDtoRequest actualizarEstadoPaquete(EnvioDtoUpdate envioDtoUpdate) {
        Integer cedula = envioDtoUpdate.getCedula();
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedula);
        if (!empleado.isPresent()) {
            throw new ApiRequestException("El empleado con cedula " + cedula + " no existe en nuestra compañía");
        }

        Integer numGuia = envioDtoUpdate.getNumGuia();
        Optional<Envio> envioOptional = this.envioRepository.findById(numGuia);

        if (!envioOptional.isPresent()) {
            throw new ApiRequestException("El numero de guia no existe");
        }

        String tipoEmpleado = empleado.get().getTipoEmpleado();
        String estadoEnvio = envioDtoUpdate.getEstadoEnvio();
        String estadoEnvioActual = envioOptional.get().getEstadoEnvio();

        if (tipoEmpleado.equals("REPARTIDOR") || tipoEmpleado.equals("COORDINADOR")) {
            if (estadoEnvioActual.equals("RECIBIDO") && estadoEnvio.equals("EN RUTA")) {
                envioOptional.get().setEstadoEnvio(estadoEnvio);
                this.envioRepository.save(envioOptional.get());
            } else if (estadoEnvioActual.equals("EN RUTA") && estadoEnvio.equals("ENTREGADO")) {
                envioOptional.get().setEstadoEnvio(estadoEnvio);
                this.envioRepository.save(envioOptional.get());
            } else if (estadoEnvioActual.equals("RECIBIDO") && estadoEnvio.equals("ENTREGADO")) {
                throw new ApiRequestException("El cambio de estado no cumple con las validaciones");
            } else {
                throw new ApiRequestException("El tipo de estado no existe en la base de datos: " + estadoEnvio);
            }
        } else {
            throw new ApiRequestException("El tipo de empleado no tiene permiso para actualizar el estado del envío");
        }

        EnvioDtoRequest envioDtoRequest = new EnvioDtoRequest();
        envioDtoRequest.setNumGuia(envioOptional.get().getNumGuia());
        envioDtoRequest.setEstadoEnvio(envioOptional.get().getEstadoEnvio());

        return envioDtoRequest;
    }


    public List<Envio> filtrarPorEstado(String estadoEnvio, Integer cedula) {
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedula);
        if (!empleado.isPresent()) {
            throw new ApiRequestException("La cedula del empleado no existe");
        }
        return this.envioRepository.envioPorEstado(estadoEnvio);
    }
}





