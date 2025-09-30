package com.reserva_turnos.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JsonIgnore
    private Comercio comercio;
    
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Turno> turnos;
    
    // Constructor adicional para casos específicos
    public Servicio(String nomServicio, LocalTime horaApertura, LocalTime horaCierre, 
                   Integer duracion, Comercio comercio) {
        this.nomServicio = nomServicio;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.duracion = duracion;
        this.comercio = comercio;
    }
}
