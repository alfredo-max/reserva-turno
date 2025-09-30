package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.entity.Servicio;
import com.reserva_turnos.demo.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioService {
    
    @Autowired
    private ServicioRepository servicioRepository;
    
    /**
     * Obtener servicios por comercio
     * @param idComercio ID del comercio
     * @return lista de servicios del comercio
     */
    public List<Servicio> obtenerServiciosPorComercio(Long idComercio) {
        return servicioRepository.findByComercioIdComercio(idComercio);
    }
}
