package com.example.Proyecto.Integrador.Service;

import com.example.Proyecto.Integrador.Model.Paquete;
import com.example.Proyecto.Integrador.Repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteService {

    private PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    public Paquete crearPaquete(Paquete paquete){
        return this.paqueteRepository.save(paquete);
    }
}
