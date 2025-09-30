-- Procedimientos almacenados para el Sistema de Reserva de Turnos
-- Compatible con Oracle Database

-- Procedimiento almacenado para generar turnos
CREATE OR REPLACE PROCEDURE GENERAR_TURNOS_SERVICIO(
    p_fecha_inicio IN DATE,
    p_fecha_fin IN DATE,
    p_id_servicio IN NUMBER,
    p_cursor OUT SYS_REFCURSOR
)
IS
    v_hora_apertura TIMESTAMP;
    v_hora_cierre TIMESTAMP;
    v_duracion NUMBER;
    v_fecha_actual DATE;
    v_hora_actual TIMESTAMP;
    v_hora_fin_turno TIMESTAMP;
    v_contador NUMBER := 0;
    
    -- Excepción personalizada para servicio no encontrado
    servicio_no_encontrado EXCEPTION;
    
BEGIN
    -- Consultar datos del servicio
    SELECT hora_apertura, hora_cierre, duracion
    INTO v_hora_apertura, v_hora_cierre, v_duracion
    FROM servicios
    WHERE id_servicio = p_id_servicio;
    
    -- Si no se encuentra el servicio, lanzar excepción
    IF SQL%NOTFOUND THEN
        RAISE servicio_no_encontrado;
    END IF;
    
    -- Inicializar fecha actual
    v_fecha_actual := p_fecha_inicio;
    
    -- Bucle para generar turnos desde fecha inicio hasta fecha fin
    WHILE v_fecha_actual <= p_fecha_fin LOOP
        
        -- Verificar si ya existen turnos para este servicio en esta fecha
        SELECT COUNT(*)
        INTO v_contador
        FROM turnos
        WHERE id_servicio = p_id_servicio
        AND TRUNC(fecha_turno) = v_fecha_actual;
        
        -- Si no existen turnos para esta fecha, generarlos
        IF v_contador = 0 THEN
            -- Establecer hora inicial del día
            v_hora_actual := TO_TIMESTAMP(TO_CHAR(v_fecha_actual, 'YYYY-MM-DD') || ' ' || 
                           TO_CHAR(v_hora_apertura, 'HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS');
            
            -- Establecer hora de cierre del día  
            v_hora_cierre := TO_TIMESTAMP(TO_CHAR(v_fecha_actual, 'YYYY-MM-DD') || ' ' || 
                           TO_CHAR(v_hora_cierre, 'HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS');
            
            -- Generar turnos del día
            WHILE v_hora_actual + INTERVAL '1' MINUTE * v_duracion <= v_hora_cierre LOOP
                v_hora_fin_turno := v_hora_actual + INTERVAL '1' MINUTE * v_duracion;
                
                -- Insertar el turno
                INSERT INTO turnos (
                    id_servicio,
                    fecha_turno,
                    hora_inicio,
                    hora_fin,
                    estado
                ) VALUES (
                    p_id_servicio,
                    v_fecha_actual,
                    v_hora_actual,
                    v_hora_fin_turno,
                    'DISPONIBLE'
                );
                
                -- Avanzar a la siguiente hora
                v_hora_actual := v_hora_fin_turno;
            END LOOP;
        END IF;
        
        -- Avanzar al siguiente día
        v_fecha_actual := v_fecha_actual + 1;
    END LOOP;
    
    -- Devolver los turnos generados
    OPEN p_cursor FOR
        SELECT 
            t.id_turno,
            t.id_servicio,
            t.fecha_turno,
            t.hora_inicio,
            t.hora_fin,
            t.estado,
            s.nom_servicio,
            c.nom_comercio
        FROM turnos t
        JOIN servicios s ON t.id_servicio = s.id_servicio
        JOIN comercios c ON s.id_comercio = c.id_comercio
        WHERE t.id_servicio = p_id_servicio
        AND t.fecha_turno BETWEEN p_fecha_inicio AND p_fecha_fin
        ORDER BY t.fecha_turno, t.hora_inicio;
    
    COMMIT;
    
EXCEPTION
    WHEN servicio_no_encontrado THEN
        RAISE_APPLICATION_ERROR(-20001, 'Servicio no encontrado con ID: ' || p_id_servicio);
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'Error al generar turnos: ' || SQLERRM);
        
END GENERAR_TURNOS_SERVICIO;
/

-- Procedimiento adicional para obtener servicios de un comercio
CREATE OR REPLACE PROCEDURE OBTENER_SERVICIOS_COMERCIO(
    p_id_comercio IN NUMBER,
    p_cursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cursor FOR
        SELECT 
            s.id_servicio,
            s.nom_servicio,
            s.hora_apertura,
            s.hora_cierre,
            s.duracion,
            c.nom_comercio
        FROM servicios s
        JOIN comercios c ON s.id_comercio = c.id_comercio
        WHERE s.id_comercio = p_id_comercio
        ORDER BY s.nom_servicio;
END OBTENER_SERVICIOS_COMERCIO;
/

-- Procedimiento para obtener todos los comercios
CREATE OR REPLACE PROCEDURE OBTENER_COMERCIOS(
    p_cursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cursor FOR
        SELECT 
            id_comercio,
            nom_comercio,
            aforo_maximo
        FROM comercios
        ORDER BY nom_comercio;
END OBTENER_COMERCIOS;
/
