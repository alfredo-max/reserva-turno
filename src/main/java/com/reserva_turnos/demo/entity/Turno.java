package com.reserva_turnos.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "turnos")
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
    
    // Constructores
    public Turno() {}
    
    public Turno(LocalDate fechaTurno, LocalTime horaInicio, LocalTime horaFin, 
                EstadoTurno estado, Servicio servicio) {
        this.fechaTurno = fechaTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.servicio = servicio;
    }
    
    // Getters y Setters
    public Long getIdTurno() {
        return idTurno;
    }
    
    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }
    
    public LocalDate getFechaTurno() {
        return fechaTurno;
    }
    
    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
    
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public LocalTime getHoraFin() {
        return horaFin;
    }
    
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public EstadoTurno getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }
    
    public Servicio getServicio() {
        return servicio;
    }
    
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @Override
    public String toString() {
        return "Turno{" +
                "idTurno=" + idTurno +
                ", fechaTurno=" + fechaTurno +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", estado=" + estado +
                '}';
    }
}
