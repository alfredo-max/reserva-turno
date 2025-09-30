package com.reserva_turnos.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "comercios")
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
    
    // Constructores
    public Comercio() {}
    
    public Comercio(String nomComercio, Integer aforoMaximo) {
        this.nomComercio = nomComercio;
        this.aforoMaximo = aforoMaximo;
    }
    
    // Getters y Setters
    public Long getIdComercio() {
        return idComercio;
    }
    
    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }
    
    public String getNomComercio() {
        return nomComercio;
    }
    
    public void setNomComercio(String nomComercio) {
        this.nomComercio = nomComercio;
    }
    
    public Integer getAforoMaximo() {
        return aforoMaximo;
    }
    
    public void setAforoMaximo(Integer aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }
    
    public List<Servicio> getServicios() {
        return servicios;
    }
    
    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
    
    @Override
    public String toString() {
        return "Comercio{" +
                "idComercio=" + idComercio +
                ", nomComercio='" + nomComercio + '\'' +
                ", aforoMaximo=" + aforoMaximo +
                '}';
    }
}
