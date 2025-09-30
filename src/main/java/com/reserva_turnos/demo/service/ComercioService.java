package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.repository.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComercioService {
    
    @Autowired
    private ComercioRepository comercioRepository;
    
    /**
     * Obtener todos los comercios
     * @return lista de comercios
     */
    public List<Comercio> obtenerTodosLosComercio() {
        return comercioRepository.findAll();
    }
}
