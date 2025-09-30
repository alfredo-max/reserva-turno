package com.reserva_turnos.demo.service;

import com.reserva_turnos.demo.entity.Comercio;
import com.reserva_turnos.demo.entity.Servicio;
import com.reserva_turnos.demo.repository.ServicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

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
    public void obtenerServiciosPorComercio_ConServiciosExistentes_DebeRetornarListaDeServicios() {
        // Given - Configuración del mock
        Long idComercio = 1L;
        when(servicioRepository.findByComercioIdComercio(idComercio)).thenReturn(serviciosMock);

        // When - Acción que se está probando
        List<Servicio> resultado = servicioService.obtenerServiciosPorComercio(idComercio);

        // Then - Verificación de resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Servicio Test 1", resultado.get(0).getNomServicio());
        assertEquals("Servicio Test 2", resultado.get(1).getNomServicio());
        assertEquals(LocalTime.of(9, 0), resultado.get(0).getHoraApertura());
        assertEquals(LocalTime.of(10, 0), resultado.get(1).getHoraApertura());

        // Verificar que el método del repositorio se llamó exactamente una vez con el parámetro correcto
        verify(servicioRepository, times(1)).findByComercioIdComercio(idComercio);
        verifyNoMoreInteractions(servicioRepository);
    }

    @Test
    public void obtenerServiciosPorComercio_SinServiciosExistentes_DebeRetornarListaVacia() {
        // Given - Configuración del mock
        Long idComercio = 999L; // ID que no existe
        when(servicioRepository.findByComercioIdComercio(idComercio)).thenReturn(Collections.emptyList());

        // When - Acción que se está probando
        List<Servicio> resultado = servicioService.obtenerServiciosPorComercio(idComercio);

        // Then - Verificación de resultados
        assertNotNull(resultado);
        assertEquals(0, resultado.size());

        // Verificar que el método del repositorio se llamó exactamente una vez con el parámetro correcto
        verify(servicioRepository, times(1)).findByComercioIdComercio(idComercio);
        verifyNoMoreInteractions(servicioRepository);
    }
}
