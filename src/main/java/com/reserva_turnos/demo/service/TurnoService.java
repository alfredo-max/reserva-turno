package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.dto.TurnoInfoDTO;
import com.reserva_turnos.demo.entity.Turno;
import com.reserva_turnos.demo.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TurnoService {
    
    @Autowired
    private TurnoRepository turnoRepository;
    
    /**
     * Obtener turnos por servicio y rango de fechas
     *
     * @param idServicio  ID del servicio
     * @param fechaInicio fecha de inicio 
     * @param fechaFin    fecha de fin
     * @return lista de turnos
     */
    public List<Turno> obtenerTurnosPorServicio(Long idServicio, LocalDate fechaInicio, LocalDate fechaFin) {
        return turnoRepository.findByServicioIdServicioAndFechaTurnoBetween(
                idServicio, fechaInicio, fechaFin);
    }

    /**
     * Obtener turnos paginados
     * @param pageable configuración de paginación y ordenación
     * @return página de turnos
     */
    public Page<TurnoInfoDTO> obtenerTurnosPaginados(Pageable pageable) {
        return turnoRepository.obtenerTurnosPaginados(pageable);
    }
}
