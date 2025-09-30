package com.reserva_turnos.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO simplificado para listar los turnos con la información básica
 * Interfaz de proyección para Spring Data JPA
 */
public interface TurnoInfoDTO {
    Long getId();
    String getComercio();
    String getServicio();
    LocalDate getFechaTurno();
    LocalTime getHoraInicio();
    LocalTime getHoraFin();
}
