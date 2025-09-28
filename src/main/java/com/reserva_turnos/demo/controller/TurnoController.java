package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.dto.GenerarTurnosRequest;
import com.reserva_turnos.demo.entity.Turno;
import com.reserva_turnos.demo.service.GeneracionTurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {
    
    @Autowired
    private GeneracionTurnoService generacionTurnoService;
    
    /**
     * Endpoint principal para generar turnos - usado por el botón "Generar"
     * @param request datos de la petición (fechaInicio, fechaFin, idServicio)
     * @return respuesta con los turnos generados
     */
    @PostMapping("/generar")
    public ResponseEntity<?> generarTurnos(@Valid @RequestBody GenerarTurnosRequest request) {
        try {
            List<Turno> turnosGenerados = generacionTurnoService.generarTurnos(
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
     * @param idServicio ID del servicio
     * @param fechaInicio fecha de inicio (opcional)
     * @param fechaFin fecha de fin (opcional)
     * @return lista de turnos
     */
    @GetMapping("/servicio/{idServicio}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorServicio(
            @PathVariable Long idServicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        
        try {
            List<Turno> turnos;
            
            if (fechaInicio != null && fechaFin != null) {
                // Si se proporcionan ambas fechas, obtener turnos en ese rango
                turnos = generacionTurnoService.generarTurnos(fechaInicio, fechaFin, idServicio);
            } else if (fechaInicio != null) {
                // Si solo se proporciona fecha inicio, obtener turnos de esa fecha
                turnos = generacionTurnoService.obtenerTurnosPorFecha(idServicio, fechaInicio);
            } else {
                // Si no se proporcionan fechas, obtener turnos de hoy
                turnos = generacionTurnoService.obtenerTurnosPorFecha(idServicio, LocalDate.now());
            }
            
            return ResponseEntity.ok(turnos);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    

}
