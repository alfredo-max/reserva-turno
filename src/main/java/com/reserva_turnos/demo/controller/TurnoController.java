package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.dto.GenerarTurnosRequest;
import com.reserva_turnos.demo.dto.TurnoInfoDTO;
import com.reserva_turnos.demo.dto.TurnoResultDTO;
import com.reserva_turnos.demo.entity.Turno;
import com.reserva_turnos.demo.service.GeneracionTurnoService;
import com.reserva_turnos.demo.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Obtener todos los turnos sin filtrado - para mostrar en la tabla
     * Retorna un DTO simplificado con los campos: Comercio, Servicio, Fecha Turno, Hora Inicio y Hora Fin
     *
     * @return lista de DTOs con la información básica de los turnos
     */
    @GetMapping("/todos")
    public List<TurnoInfoDTO> obtenerTodosTurnos() {
        return turnoService.obtenerTodosTurnos();
    }
}

