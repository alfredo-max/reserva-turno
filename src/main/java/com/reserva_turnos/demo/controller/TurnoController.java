package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.dto.GenerarTurnosRequest;
import com.reserva_turnos.demo.dto.TurnoInfoDTO;
import com.reserva_turnos.demo.dto.TurnoResultDTO;
import com.reserva_turnos.demo.entity.Turno;
import com.reserva_turnos.demo.service.GeneracionTurnoService;
import com.reserva_turnos.demo.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {

    @Autowired
    private GeneracionTurnoService generacionTurnoService;

    @Autowired
    private TurnoService turnoService;

    /**
     * Endpoint principal para generar turnos - usado por el botón "Generar"
     *
     * @param request datos de la petición (fechaInicio, fechaFin, idServicio)
     * @return respuesta con los turnos generados
     */
    @PostMapping("/generar")
    public ResponseEntity<?> generarTurnos(@Valid @RequestBody GenerarTurnosRequest request) {
        try {
            List<TurnoResultDTO> turnosGenerados = generacionTurnoService.generarTurnos(
                    request.getFechaInicio(),
                    request.getFechaFin(),
                    request.getIdServicio()
            );

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Turnos generados exitosamente");
            respuesta.put("cantidadTurnos", turnosGenerados.size());
            respuesta.put("turnos", turnosGenerados);

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al generar turnos");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Obtener todos los turnos con paginación - para mostrar en la tabla
     * Retorna un DTO simplificado con los campos: Comercio, Servicio, Fecha Turno, Hora Inicio y Hora Fin
     *
     * @param page número de página (0-indexed, opcional, por defecto 0)
     * @param size tamaño de la página (opcional, por defecto 10)
     * @return respuesta que contiene la página de turnos y metadatos de paginación
     */
    @GetMapping("/todos")
    public ResponseEntity<Map<String, Object>> obtenerTodosTurnos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        // Crear configuración de paginación ordenada por fecha y hora
        Pageable pageable = PageRequest.of(page, size, 
                Sort.by("fechaTurno").ascending().and(Sort.by("horaInicio").ascending()));
        
        // Obtener la página de resultados
        Page<TurnoInfoDTO> turnoPage = turnoService.obtenerTurnosPaginados(pageable);
        
        // Preparar respuesta con metadatos de paginación
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("turnos", turnoPage.getContent());
        respuesta.put("paginaActual", turnoPage.getNumber());
        respuesta.put("totalItems", turnoPage.getTotalElements());
        respuesta.put("totalPaginas", turnoPage.getTotalPages());
        
        return ResponseEntity.ok(respuesta);
    }
}

