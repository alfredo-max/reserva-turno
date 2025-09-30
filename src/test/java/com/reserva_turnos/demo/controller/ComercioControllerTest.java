package com.reserva_turnos.demo.controller;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.service.ComercioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComercioControllerTest {

    @Mock
    private ComercioService comercioService;

    @InjectMocks
    private ComercioController comercioController;

    private List<Comercio> comerciosMock;

    @BeforeEach
    public void setUp() {
        // Preparar datos de prueba
        Comercio comercio1 = Comercio.builder()
                .idComercio(1L)
                .nomComercio("Comercio Test 1")
                .aforoMaximo(50)
                .build();

        Comercio comercio2 = Comercio.builder()
                .idComercio(2L)
                .nomComercio("Comercio Test 2")
                .aforoMaximo(30)
                .build();

        comerciosMock = Arrays.asList(comercio1, comercio2);
    }

    @Test
    public void obtenerTodosComercio_DebeRetornarListaDeComerciosConEstadoOk() {
        // Given - Configuración del mock
        when(comercioService.obtenerTodosLosComercio()).thenReturn(comerciosMock);

        // When - Acción que se está probando
        ResponseEntity<List<Comercio>> respuesta = comercioController.obtenerTodosComercio();

        // Then - Verificación de resultados
        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
        assertEquals("Comercio Test 1", respuesta.getBody().get(0).getNomComercio());
        assertEquals("Comercio Test 2", respuesta.getBody().get(1).getNomComercio());

        // Verificar que el método obtenerTodosLosComercio del servicio se llamó exactamente una vez
        verify(comercioService, times(1)).obtenerTodosLosComercio();
        verifyNoMoreInteractions(comercioService);
    }
}
