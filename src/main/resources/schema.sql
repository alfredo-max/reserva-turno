-- Script SQL para crear las tablas del Sistema de Reserva de Turnos
-- Compatible con Oracle Database

-- Crear tabla comercios
CREATE TABLE comercios (
    id_comercio NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom_comercio VARCHAR2(100) NOT NULL,
    aforo_maximo NUMBER NOT NULL,
    CONSTRAINT chk_aforo_positivo CHECK (aforo_maximo > 0)
);

-- Crear tabla servicios
CREATE TABLE servicios (
    id_servicio NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_comercio NUMBER NOT NULL,
    nom_servicio VARCHAR2(100) NOT NULL,
    hora_apertura TIMESTAMP NOT NULL,
    hora_cierre TIMESTAMP NOT NULL,
    duracion NUMBER NOT NULL,
    CONSTRAINT fk_servicio_comercio FOREIGN KEY (id_comercio) REFERENCES comercios(id_comercio),
    CONSTRAINT chk_duracion_positiva CHECK (duracion > 0),
    CONSTRAINT chk_horas_validas CHECK (hora_cierre > hora_apertura)
);

-- Crear tabla turnos
CREATE TABLE turnos (
    id_turno NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_servicio NUMBER NOT NULL,
    fecha_turno DATE NOT NULL,
    hora_inicio TIMESTAMP NOT NULL,
    hora_fin TIMESTAMP NOT NULL,
    estado VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_turno_servicio FOREIGN KEY (id_servicio) REFERENCES servicios(id_servicio),
    CONSTRAINT chk_estado_valido CHECK (estado IN ('DISPONIBLE', 'RESERVADO', 'COMPLETADO', 'CANCELADO')),
    CONSTRAINT chk_horas_turno_validas CHECK (hora_fin > hora_inicio)
);

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_servicios_comercio ON servicios(id_comercio);
CREATE INDEX idx_turnos_servicio ON turnos(id_servicio);
CREATE INDEX idx_turnos_fecha ON turnos(fecha_turno);
CREATE INDEX idx_turnos_estado ON turnos(estado);
CREATE INDEX idx_turnos_servicio_fecha ON turnos(id_servicio, fecha_turno);

-- Comentarios en las tablas
COMMENT ON TABLE comercios IS 'Tabla que almacena información de los comercios';
COMMENT ON TABLE servicios IS 'Tabla que almacena los servicios ofrecidos por cada comercio';
COMMENT ON TABLE turnos IS 'Tabla que almacena los turnos disponibles y reservados para cada servicio';

-- Comentarios en las columnas principales
COMMENT ON COLUMN comercios.aforo_maximo IS 'Capacidad máxima de personas que puede atender el comercio';
COMMENT ON COLUMN servicios.duracion IS 'Duración del servicio en minutos';
COMMENT ON COLUMN turnos.estado IS 'Estado del turno: DISPONIBLE, RESERVADO, COMPLETADO, CANCELADO';
