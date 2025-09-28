package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.entity.Turno;
import com.reserva_turnos.demo.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class GeneracionTurnoService {
    
    @Autowired
    private TurnoRepository turnoRepository;
    
    /**
     * Genera turnos usando el procedimiento almacenado
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param idServicio ID del servicio
     * @return lista de turnos generados
     */
    public List<Turno> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio) {
        return turnoRepository.generarTurnosServicio(fechaInicio, fechaFin, idServicio);
    }
    
    /**
     * Obtiene turnos de un servicio en una fecha específica
     * @param idServicio ID del servicio
     * @param fecha fecha a consultar
     * @return lista de turnos
     */
    @Transactional(readOnly = true)
    public List<Turno> obtenerTurnosPorFecha(Long idServicio, LocalDate fecha) {
        return turnoRepository.findByServicioIdServicioAndFechaTurno(idServicio, fecha);
    }
}
