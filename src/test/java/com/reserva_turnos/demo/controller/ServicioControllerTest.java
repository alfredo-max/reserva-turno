package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.entity.Servicio;
import com.reserva_turnos.demo.service.ServicioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicioControllerTest {

    @Mock
    private ServicioService servicioService;

    @InjectMocks
    private ServicioController servicioController;

    private Comercio comercioMock;
    private List<Servicio> serviciosMock;

    @BeforeEach
    public void setUp() {
        // Preparar datos de prueba
        comercioMock = Comercio.builder()
                .idComercio(1L)
                .nomComercio("Comercio Test")
                .aforoMaximo(50)
                .build();

        Servicio servicio1 = Servicio.builder()
                .idServicio(1L)
                .nomServicio("Servicio Test 1")
                .horaApertura(LocalTime.of(9, 0))
                .horaCierre(LocalTime.of(18, 0))
                .duracion(30)
                .comercio(comercioMock)
                .build();

        Servicio servicio2 = Servicio.builder()
                .idServicio(2L)
                .nomServicio("Servicio Test 2")
                .horaApertura(LocalTime.of(10, 0))
                .horaCierre(LocalTime.of(19, 0))
                .duracion(45)
                .comercio(comercioMock)
                .build();

        serviciosMock = Arrays.asList(servicio1, servicio2);
    }

    @Test
    public void obtenerServiciosPorComercio_ConServiciosExistentes_DebeRetornarListaDeServiciosConEstadoOk() {
        // Given - Configuración del mock
        Long idComercio = 1L;
        when(servicioService.obtenerServiciosPorComercio(idComercio)).thenReturn(serviciosMock);

        // When - Acción que se está probando
        ResponseEntity<List<Servicio>> respuesta = servicioController.obtenerServiciosPorComercio(idComercio);

        // Then - Verificación de resultados
        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(2, respuesta.getBody().size());
        assertEquals("Servicio Test 1", respuesta.getBody().get(0).getNomServicio());
        assertEquals("Servicio Test 2", respuesta.getBody().get(1).getNomServicio());

        // Verificar que el método del servicio se llamó exactamente una vez con el parámetro correcto
        verify(servicioService, times(1)).obtenerServiciosPorComercio(idComercio);
        verifyNoMoreInteractions(servicioService);
    }

    @Test
    public void obtenerServiciosPorComercio_SinServiciosExistentes_DebeRetornarListaVaciaConEstadoOk() {
        // Given - Configuración del mock
        Long idComercio = 999L; // ID que no existe
        when(servicioService.obtenerServiciosPorComercio(idComercio)).thenReturn(Collections.emptyList());

        // When - Acción que se está probando
        ResponseEntity<List<Servicio>> respuesta = servicioController.obtenerServiciosPorComercio(idComercio);

        // Then - Verificación de resultados
        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(0, respuesta.getBody().size());

        // Verificar que el método del servicio se llamó exactamente una vez con el parámetro correcto
        verify(servicioService, times(1)).obtenerServiciosPorComercio(idComercio);
        verifyNoMoreInteractions(servicioService);
    }
}
