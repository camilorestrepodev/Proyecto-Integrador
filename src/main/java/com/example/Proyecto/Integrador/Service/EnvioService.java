package com.example.Proyecto.Integrador.Service;

import com.example.Proyecto.Integrador.Dto.EnvioDto;
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

    public EnvioDto crearEnvio(EnvioDto envioDto) {
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
        if (clienteOptional.isPresent()) {
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
            envioDto.setNumGuia(envio1.getNumGuia());
            return envioDto;
        } else {
            throw new ApiRequestException("El cliente con cedula " + envioDto.getCedula() + " debe de estar registrado para poder enviar el paquete.");
        }
    }

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
        Integer numeroGuia = envio.get().getNumGuia();
        Integer cedula = envio.get().getCliente().getCedula();
        String nombre = envio.get().getCliente().getNombre();
        String ciudadOrigen = envio.get().getCiudadOrigen();
        String ciudadDestino = envio.get().getCiudadDestino();
        String direccionDestino = envio.get().getDireccionDestino();
        String nombrePersona = envio.get().getNombrePersona();
        Integer celular = envio.get().getNumeroPersona();
        Integer valorDeclarado = envio.get().getPaquete().getValorDeclarado();
        Double peso = envio.get().getPaquete().getPeso();
        Integer valorEnvio = envio.get().getValorEnvio();
        EnvioDto envioDto = new EnvioDto(
                cedula, nombre, ciudadOrigen,
                ciudadDestino, direccionDestino, nombrePersona,
                celular, peso, valorDeclarado);
        envioDto.setNumGuia(numeroGuia);
        String estadoEnvio = envio.get().getEstadoEnvio();
        if (estadoEnvio.equals("RECIBIDO")) {
            envio.get().setEstadoEnvio("EN RUTA");
            envioDto.setEstadoEnvio("EN RUTA");
            this.envioRepository.save(envio.get());
        } else {
            envioDto.setEstadoEnvio(estadoEnvio);
        }
        envioDto.setValorEnvio(valorEnvio);
        return envioDto;
    }


    public EnvioDto actualizarEstadoPaquete(EnvioDto envioDto) {
        Integer cedula = envioDto.getCedula();
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedula);
        if (!empleado.isPresent()) {
            throw new ApiRequestException("El empleado con cedula " + cedula + " no existe en nuestra compania");
        }
        String tipoEmpleado = empleado.get().getTipoEmpleado();
        Integer numGuia = envioDto.getNumGuia();
        Optional<Envio> envioOptional = this.envioRepository.findById(numGuia);
        if (!envioOptional.isPresent()) {
            throw new ApiRequestException("El numero de guia no existe");
        }
        String estadoEnvio = envioDto.getEstadoEnvio();
        if (tipoEmpleado.equals("REPARTIDOR") || tipoEmpleado.equals("COORDINADOR")) {
            if (envioOptional.get().getEstadoEnvio().equals("RECIBIDO") && estadoEnvio.equals("EN RUTA")) {
                envioOptional.get().setEstadoEnvio(estadoEnvio);
                this.envioRepository.save(envioOptional.get());
            } else if (envioOptional.get().getEstadoEnvio().equals("EN RUTA") && estadoEnvio.equals("ENTREGADO")) {
                envioOptional.get().setEstadoEnvio(estadoEnvio);
                this.envioRepository.save(envioOptional.get());
            } else if (envioOptional.get().getEstadoEnvio().equals("RECIBIDO") && estadoEnvio.equals("ENTREGADO")) {
                throw new ApiRequestException("el cambio de estado no cumple con las validaciones");
            } else {
                throw new ApiRequestException("El tipo de empleado no tiene permiso para actualizar el estado del envío");
            }
        } else {
            throw new ApiRequestException("El tipo de empleado no tiene permiso para actualizar el estado del envío");
        }
        EnvioDto envioDto1 = new EnvioDto();
        envioDto1.setNumGuia(envioOptional.get().getNumGuia());
        envioDto1.setEstadoEnvio(envioOptional.get().getEstadoEnvio());
        return envioDto1;
    }


    public List<Envio> filtrarPorEstado(String estadoEnvio, Integer cedula) {
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedula);
        if (!empleado.isPresent()) {
            throw new ApiRequestException("La cedula del empleado no existe");
        }
        return this.envioRepository.envioPorEstado(estadoEnvio);
    }
}





