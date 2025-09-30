package com.reserva_turnos.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comercios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comercio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comercio")
    private Long idComercio;
    
    @Column(name = "nom_comercio", nullable = false, length = 100)
    private String nomComercio;
    
    @Column(name = "aforo_maximo", nullable = false)
    private Integer aforoMaximo;
    
    @OneToMany(mappedBy = "comercio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Servicio> servicios;
    
    // Constructor adicional para casos específicos
    public Comercio(String nomComercio, Integer aforoMaximo) {
        this.nomComercio = nomComercio;
        this.aforoMaximo = aforoMaximo;
    }
}
