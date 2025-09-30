package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.entity.Servicio;
import com.reserva_turnos.demo.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {
    
    @Autowired
    private ServicioService servicioService;
    
    /**
     * Obtener servicios por comercio para el dropdown
     * @param idComercio ID del comercio seleccionado
     * @return lista de servicios del comercio (sin campos relación)
     */
    @GetMapping("/comercio/{idComercio}")
    public ResponseEntity<List<Servicio>> obtenerServiciosPorComercio(@PathVariable Long idComercio) {
        List<Servicio> servicios = servicioService.obtenerServiciosPorComercio(idComercio);
        return ResponseEntity.ok(servicios);
    }
}
