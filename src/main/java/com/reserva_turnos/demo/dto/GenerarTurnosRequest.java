package com.reserva_turnos.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class GenerarTurnosRequest {
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    
    @NotNull(message = "La fecha de fin es obligatoria") 
    private LocalDate fechaFin;
    
    @NotNull(message = "El ID del servicio es obligatorio")
    private Long idServicio;
    
    // Constructores
    public GenerarTurnosRequest() {}
    
    public GenerarTurnosRequest(LocalDate fechaInicio, LocalDate fechaFin, Long idServicio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idServicio = idServicio;
    }
    
    // Getters y Setters
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public Long getIdServicio() {
        return idServicio;
    }
    
    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }
    
    @Override
    public String toString() {
        return "GenerarTurnosRequest{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", idServicio=" + idServicio +
                '}';
    }
}
