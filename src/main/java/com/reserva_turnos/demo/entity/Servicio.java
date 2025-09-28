package com.reserva_turnos.demo.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;
    
    @Column(name = "nom_servicio", nullable = false, length = 100)
    private String nomServicio;
    
    @Column(name = "hora_apertura", nullable = false)
    private LocalTime horaApertura;
    
    @Column(name = "hora_cierre", nullable = false)
    private LocalTime horaCierre;
    
    @Column(name = "duracion", nullable = false)
    private Integer duracion; // Duración en minutos
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comercio", nullable = false)
    private Comercio comercio;
    
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Turno> turnos;
    
    // Constructores
    public Servicio() {}
    
    public Servicio(String nomServicio, LocalTime horaApertura, LocalTime horaCierre, 
                   Integer duracion, Comercio comercio) {
        this.nomServicio = nomServicio;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.duracion = duracion;
        this.comercio = comercio;
    }
    
    // Getters y Setters
    public Long getIdServicio() {
        return idServicio;
    }
    
    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }
    
    public String getNomServicio() {
        return nomServicio;
    }
    
    public void setNomServicio(String nomServicio) {
        this.nomServicio = nomServicio;
    }
    
    public LocalTime getHoraApertura() {
        return horaApertura;
    }
    
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }
    
    public LocalTime getHoraCierre() {
        return horaCierre;
    }
    
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }
    
    public Integer getDuracion() {
        return duracion;
    }
    
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    
    public Comercio getComercio() {
        return comercio;
    }
    
    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }
    
    public List<Turno> getTurnos() {
        return turnos;
    }
    
    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
    
    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", nomServicio='" + nomServicio + '\'' +
                ", horaApertura=" + horaApertura +
                ", horaCierre=" + horaCierre +
                ", duracion=" + duracion +
                '}';
    }
}
