package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.dto.GenerarTurnosRequest;
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
     * Obtener turnos por servicio y rango de fechas - para mostrar en la tabla
     *
     * @param idServicio  ID del servicio
     * @param fechaInicio fecha de inicio (opcional)
     * @param fechaFin    fecha de fin (opcional)
     * @return lista de turnos
     */
    // mejora: que retorne un dto a la proxima
    @GetMapping("/servicio/{idServicio}")
    public List<Turno> obtenerTurnosPorServicio(
            @PathVariable Long idServicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        // Si no se proporciona fecha inicio, usar la fecha actual
        if (fechaInicio == null) {
            fechaInicio = LocalDate.now();
        }

        // Si no se proporciona fecha fin, usar fecha inicio + 30 días
        if (fechaFin == null) {
            fechaFin = fechaInicio.plusDays(30);
        }

        // TODO: En el futuro, considerar usar DTOs para estructurar mejor la respuesta

        // Obtener turnos usando el servicio
        return turnoService.obtenerTurnosPorServicio(idServicio, fechaInicio, fechaFin);
    }
}

