package com.reserva_turnos.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.reserva_turnos.demo.entity.Turno.EstadoTurno;

/**
 * DTO para recibir los resultados del procedimiento almacenado
 */
public class TurnoResultDTO {
    
    private Long idTurno;
    private Long idServicio;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private String nomServicio;
    private String nomComercio;
    
    // Constructor por defecto
    public TurnoResultDTO() {
    }
    
    // Constructor con todos los campos
    public TurnoResultDTO(Long idTurno, Long idServicio, LocalDate fechaTurno, LocalTime horaInicio, 
                         LocalTime horaFin, String estado, String nomServicio, String nomComercio) {
        this.idTurno = idTurno;
        this.idServicio = idServicio;
        this.fechaTurno = fechaTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.nomServicio = nomServicio;
        this.nomComercio = nomComercio;
    }
    
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
    
    /**
     * Convierte este DTO a una entidad Turno
     */
    public com.reserva_turnos.demo.entity.Turno toEntity() {
        com.reserva_turnos.demo.entity.Turno turno = new com.reserva_turnos.demo.entity.Turno();
        turno.setIdTurno(this.idTurno);
        turno.setFechaTurno(this.fechaTurno);
        turno.setHoraInicio(this.horaInicio);
        turno.setHoraFin(this.horaFin);
        turno.setEstado(EstadoTurno.valueOf(this.estado));
        
        // Nota: el servicio debe ser establecido por separado ya que necesita la entidad completa
        
        return turno;
    }
    
    @Override
    public String toString() {
        return "TurnoResultDTO{" +
                "idTurno=" + idTurno +
                ", idServicio=" + idServicio +
                ", fechaTurno=" + fechaTurno +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", estado='" + estado + '\'' +
                ", nomServicio='" + nomServicio + '\'' +
                ", nomComercio='" + nomComercio + '\'' +
                '}'; 
    }
}
