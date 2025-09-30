package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.dto.TurnoResultDTO;
import com.reserva_turnos.demo.entity.Servicio;
import com.reserva_turnos.demo.entity.Turno;
import com.reserva_turnos.demo.repository.ServicioRepository;
import com.reserva_turnos.demo.repository.TurnoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneracionTurnoService {
    
    @Autowired
    private TurnoRepository turnoRepository;
    
    @Autowired
    private ServicioRepository servicioRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Genera turnos usando el procedimiento almacenado
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param idServicio ID del servicio
     * @return lista de turnos generados como DTOs
     */
    public List<TurnoResultDTO> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio) {
        // Verificar que el servicio existe
        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + idServicio));
        
        try {
            // Llamar al procedimiento almacenado usando EntityManager
            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("GENERAR_TURNOS_SERVICIO")
                    .registerStoredProcedureParameter("p_fecha_inicio", LocalDate.class, jakarta.persistence.ParameterMode.IN)
                    .registerStoredProcedureParameter("p_fecha_fin", LocalDate.class, jakarta.persistence.ParameterMode.IN)
                    .registerStoredProcedureParameter("p_id_servicio", Long.class, jakarta.persistence.ParameterMode.IN)
                    .registerStoredProcedureParameter("p_cursor", void.class, jakarta.persistence.ParameterMode.REF_CURSOR)
                    .setParameter("p_fecha_inicio", fechaInicio)
                    .setParameter("p_fecha_fin", fechaFin)
                    .setParameter("p_id_servicio", idServicio);
            
            // Ejecutar procedimiento
            query.execute();
            
            // Obtener los resultados y convertirlos a TurnoResultDTO
            List<Object[]> resultados = query.getResultList();
            List<TurnoResultDTO> turnoDTOs = new ArrayList<>();
            
            for (Object[] fila : resultados) {
                TurnoResultDTO dto = new TurnoResultDTO();
                dto.setIdTurno(((Number) fila[0]).longValue());
                dto.setIdServicio(((Number) fila[1]).longValue());
                dto.setFechaTurno(((Timestamp) fila[2]).toLocalDateTime().toLocalDate());
                dto.setHoraInicio(((Timestamp) fila[3]).toLocalDateTime().toLocalTime());
                dto.setHoraFin(((Timestamp) fila[4]).toLocalDateTime().toLocalTime());
                dto.setEstado((String) fila[5]);
                dto.setNomServicio((String) fila[6]);
                dto.setNomComercio((String) fila[7]);
                turnoDTOs.add(dto);
            }
            
            return turnoDTOs;
            
        } catch (Exception e) {
            System.err.println("Error al ejecutar procedimiento almacenado: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudieron generar los turnos: " + e.getMessage());
        }
    }
    
    
}
