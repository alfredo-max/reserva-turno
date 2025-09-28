package com.reserva_turnos.demo.repository;

import com.reserva_turnos.demo.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
    /**
     * Buscar servicios por comercio
     * @param idComercio ID del comercio
     * @return lista de servicios del comercio
     */
    List<Servicio> findByComercioIdComercio(Long idComercio);
}
