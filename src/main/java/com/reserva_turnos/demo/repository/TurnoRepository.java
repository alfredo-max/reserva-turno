package com.reserva_turnos.demo.repository;

import com.reserva_turnos.demo.dto.TurnoResultDTO;
import com.reserva_turnos.demo.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    
    
    /**
     * Buscar turnos por servicio y fecha
     * @param idServicio ID del servicio
     * @param fecha fecha de los turnos
     * @return lista de turnos del servicio en esa fecha
     */
    List<Turno> findByServicioIdServicioAndFechaTurno(Long idServicio, LocalDate fecha);
    
    /**
     * Buscar turnos por servicio entre un rango de fechas
     * @param idServicio ID del servicio
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de turnos del servicio en ese rango de fechas
     */
    List<Turno> findByServicioIdServicioAndFechaTurnoBetween(Long idServicio, LocalDate fechaInicio, LocalDate fechaFin);
    
    /**
     * Consulta nativa para obtener turnos generados usando unión de tablas
     * @param idServicio ID del servicio
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de resultados como Object[] que luego se convierten a TurnoResultDTO
     */
    @Query(value = "SELECT t.id_turno, t.id_servicio, t.fecha_turno, t.hora_inicio, t.hora_fin, t.estado, " +
                  "s.nom_servicio, c.nom_comercio " +
                  "FROM turnos t " +
                  "JOIN servicios s ON t.id_servicio = s.id_servicio " +
                  "JOIN comercios c ON s.id_comercio = c.id_comercio " +
                  "WHERE t.id_servicio = :idServicio " +
                  "AND t.fecha_turno BETWEEN :fechaInicio AND :fechaFin " +
                  "ORDER BY t.fecha_turno, t.hora_inicio", nativeQuery = true)
    List<Object[]> obtenerTurnosConDetalles(@Param("idServicio") Long idServicio,
                                           @Param("fechaInicio") LocalDate fechaInicio,
                                           @Param("fechaFin") LocalDate fechaFin);
}
