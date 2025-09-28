package com.reserva_turnos.demo.repository;

import com.reserva_turnos.demo.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    
    /**
     * Llamada al procedimiento almacenado para generar turnos
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin  
     * @param idServicio ID del servicio
     * @return lista de turnos generados
     */
    @Procedure(name = "generar_turnos_servicio")
    List<Turno> generarTurnosServicio(@Param("p_fecha_inicio") LocalDate fechaInicio,
                                     @Param("p_fecha_fin") LocalDate fechaFin,
                                     @Param("p_id_servicio") Long idServicio);
    
    /**
     * Buscar turnos por servicio y fecha
     * @param idServicio ID del servicio
     * @param fecha fecha de los turnos
     * @return lista de turnos del servicio en esa fecha
     */
    List<Turno> findByServicioIdServicioAndFechaTurno(Long idServicio, LocalDate fecha);
}
