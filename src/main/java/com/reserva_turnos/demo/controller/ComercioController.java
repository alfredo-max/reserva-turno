package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.service.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comercios")
@CrossOrigin(origins = "*")
public class ComercioController {
    
    @Autowired
    private ComercioService comercioService;
    
    /**
     * Obtener todos los comercios para el dropdown sin incluir sus servicios
     * @return lista de comercios sin servicios (excluidos por @JsonIgnore)
     */
    @GetMapping
    public ResponseEntity<List<Comercio>> obtenerTodosComercio() {
        List<Comercio> comercios = comercioService.obtenerTodosLosComercio();
        return ResponseEntity.ok(comercios);
    }
}
