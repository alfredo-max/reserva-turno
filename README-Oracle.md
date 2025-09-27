# Configuración de Oracle Database con Docker

Este proyecto utiliza Oracle Database XE en Docker para el desarrollo y pruebas.

## Requisitos previos

- Docker y Docker Compose instalados
- Java 17
- Gradle

## Iniciar la base de datos Oracle

```bash
# Iniciar la base de datos Oracle
docker-compose up -d oracle-db

# Ver los logs de la base de datos
docker-compose logs -f oracle-db

# Parar los servicios
docker-compose down

# Parar y eliminar volúmenes (reinicio completo)
docker-compose down -v
```

## Información de conexión

- **Host:** localhost
- **Puerto:** 1521
- **SID:** XE
- **Usuario:** SYSTEM
- **Contraseña:** Oracle123
- **Base de datos:** RESERVADB

## Herramientas de administración

### SQL*Plus (desde el contenedor)
```bash
docker exec -it oracle-reserva-turno sqlplus SYSTEM/Oracle123@XE
```

## Inicialización

Los scripts SQL en el directorio `init-scripts/` se ejecutan automáticamente cuando se crea el contenedor por primera vez.

## Configuración de Spring Boot

La configuración está en `src/main/resources/application.properties`:
- Hibernate está configurado para crear/actualizar tablas automáticamente
- Se muestran las consultas SQL en los logs para debugging
- Pool de conexiones configurado con HikariCP

## Ejecutar la aplicación

```bash
# Asegúrate de que Oracle esté ejecutándose
docker-compose up -d oracle-db

# Ejecutar la aplicación Spring Boot
./gradlew bootRun

# O compilar y ejecutar
./gradlew build
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

## Troubleshooting

1. **Error de conexión:** Asegúrate de que el contenedor esté ejecutándose y saludable
2. **Timeout:** La primera inicialización puede tomar varios minutos
3. **Puerto ocupado:** Cambia los puertos en docker-compose.yml si están en uso
