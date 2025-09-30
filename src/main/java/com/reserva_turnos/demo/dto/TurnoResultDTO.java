package com.reserva_turnos.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.reserva_turnos.demo.entity.Turno.EstadoTurno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para recibir los resultados del procedimiento almacenado
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoResultDTO {
    
    private Long idTurno;
    private Long idServicio;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private String nomServicio;
    private String nomComercio;
    
    /**
     * Constructor para mapear desde un Object[] devuelto por el procedimiento almacenado
     * La estructura esperada del array es:
     * [0] - id_turno (Long)
     * [1] - id_servicio (Long)
     * [2] - fecha_turno (LocalDate)
     * [3] - hora_inicio (LocalTime)
     * [4] - hora_fin (LocalTime)
     * [5] - estado (String)
     * [6] - nom_servicio (String)
     * [7] - nom_comercio (String)
     */
    public TurnoResultDTO(Object[] objArray) {
        if (objArray != null && objArray.length >= 8) {
            this.idTurno = objArray[0] != null ? ((Number) objArray[0]).longValue() : null;
            this.idServicio = objArray[1] != null ? ((Number) objArray[1]).longValue() : null;
            this.fechaTurno = (LocalDate) objArray[2];
            this.horaInicio = (LocalTime) objArray[3];
            this.horaFin = (LocalTime) objArray[4];
            this.estado = (String) objArray[5];
            this.nomServicio = (String) objArray[6];
            this.nomComercio = (String) objArray[7];
        }
    }
    
    /**
     * Convierte este DTO a una entidad Turno
     */
    public com.reserva_turnos.demo.entity.Turno toEntity() {
        return com.reserva_turnos.demo.entity.Turno.builder()
            .idTurno(this.idTurno)
            .fechaTurno(this.fechaTurno)
            .horaInicio(this.horaInicio)
            .horaFin(this.horaFin)
            .estado(EstadoTurno.valueOf(this.estado))
            // Nota: el servicio debe ser establecido por separado ya que necesita la entidad completa
            .build();
    }
}
