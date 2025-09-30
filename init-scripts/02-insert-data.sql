-- Datos de prueba para el Sistema de Reserva de Turnos

-- Insertar comercios
INSERT INTO comercios (nom_comercio, aforo_maximo) VALUES ('Car Center', 50);
INSERT INTO comercios (nom_comercio, aforo_maximo) VALUES ('Centro Diseño', 30);
INSERT INTO comercios (nom_comercio, aforo_maximo) VALUES ('Spa Relax', 20);

-- Insertar servicios para Car Center (id_comercio = 1)
INSERT INTO servicios (id_comercio, nom_servicio, hora_apertura, hora_cierre, duracion) 
VALUES (1, 'Lavado General', TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 30);

INSERT INTO servicios (id_comercio, nom_servicio, hora_apertura, hora_cierre, duracion) 
VALUES (1, 'Lavado Premium', TO_TIMESTAMP('08:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('17:00:00', 'HH24:MI:SS'), 60);

-- Insertar servicios para Centro Diseño (id_comercio = 2)
INSERT INTO servicios (id_comercio, nom_servicio, hora_apertura, hora_cierre, duracion) 
VALUES (2, 'Diseño Cocina', TO_TIMESTAMP('08:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('17:00:00', 'HH24:MI:SS'), 120);

INSERT INTO servicios (id_comercio, nom_servicio, hora_apertura, hora_cierre, duracion) 
VALUES (2, 'Diseño Baño', TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('16:00:00', 'HH24:MI:SS'), 90);

-- Insertar servicios para Spa Relax (id_comercio = 3)
INSERT INTO servicios (id_comercio, nom_servicio, hora_apertura, hora_cierre, duracion) 
VALUES (3, 'Masaje Relajante', TO_TIMESTAMP('10:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('19:00:00', 'HH24:MI:SS'), 45);

INSERT INTO servicios (id_comercio, nom_servicio, hora_apertura, hora_cierre, duracion) 
VALUES (3, 'Facial Rejuvenecedor', TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 60);

COMMIT;
