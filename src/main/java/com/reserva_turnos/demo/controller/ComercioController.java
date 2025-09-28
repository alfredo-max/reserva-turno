package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.repository.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comercios")
@CrossOrigin(origins = "*")
public class ComercioController {
    
    @Autowired
    private ComercioRepository comercioRepository;
    
    /**
     * Obtener todos los comercios para el dropdown
     * @return lista de comercios
     */
    @GetMapping
    public ResponseEntity<List<Comercio>> obtenerTodosComercio() {
        List<Comercio> comercios = comercioRepository.findAll();
        return ResponseEntity.ok(comercios);
    }
}
