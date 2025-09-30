package com.reserva_turnos.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turnos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Long idTurno;
    
    @Column(name = "fecha_turno", nullable = false)
    private LocalDate fechaTurno;
    
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;
    
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoTurno estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    @JsonIgnore
    private Servicio servicio;
    
    // Enum para estado del turno
    public enum EstadoTurno {
        DISPONIBLE,
        RESERVADO,
        COMPLETADO,
        CANCELADO
    }
    
    // Constructor adicional para casos específicos
    public Turno(LocalDate fechaTurno, LocalTime horaInicio, LocalTime horaFin, 
                EstadoTurno estado, Servicio servicio) {
        this.fechaTurno = fechaTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.servicio = servicio;
    }
}
