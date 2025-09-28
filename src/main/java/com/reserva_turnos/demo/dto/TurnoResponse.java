package com.reserva_turnos.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoResponse {
    
    private Long idTurno;
    private Long idServicio;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private String nomServicio;
    private String nomComercio;
    
    // Constructores
    public TurnoResponse() {}
    
    public TurnoResponse(Long idTurno, Long idServicio, LocalDate fechaTurno, 
                        LocalTime horaInicio, LocalTime horaFin, String estado,
                        String nomServicio, String nomComercio) {
        this.idTurno = idTurno;
        this.idServicio = idServicio;
        this.fechaTurno = fechaTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.nomServicio = nomServicio;
        this.nomComercio = nomComercio;
    }
    
    // Getters y Setters
    public Long getIdTurno() {
        return idTurno;
    }
    
    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }
    
    public Long getIdServicio() {
        return idServicio;
    }
    
    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
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
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getNomServicio() {
        return nomServicio;
    }
    
    public void setNomServicio(String nomServicio) {
        this.nomServicio = nomServicio;
    }
    
    public String getNomComercio() {
        return nomComercio;
    }
    
    public void setNomComercio(String nomComercio) {
        this.nomComercio = nomComercio;
    }
}
