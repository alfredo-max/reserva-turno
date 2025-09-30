package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.repository.ComercioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComercioServiceTest {

    @Mock
    private ComercioRepository comercioRepository;

    @InjectMocks
    private ComercioService comercioService;

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
    public void obtenerTodosLosComercio_DebeRetornarListaDeComercio() {
        // Given - Configuración del mock
        when(comercioRepository.findAll()).thenReturn(comerciosMock);

        // When - Acción que se está probando
        List<Comercio> resultado = comercioService.obtenerTodosLosComercio();

        // Then - Verificación de resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Comercio Test 1", resultado.get(0).getNomComercio());
        assertEquals("Comercio Test 2", resultado.get(1).getNomComercio());

        // Verificar que el método findAll del repositorio se llamó exactamente una vez
        verify(comercioRepository, times(1)).findAll();
        verifyNoMoreInteractions(comercioRepository);
    }
}
