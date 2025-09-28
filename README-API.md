# API REST - Sistema de Reserva de Turnos

## Endpoints disponibles para la página de Generación de Turnos

### 1. Obtener Comercios (para dropdown)
```http
GET /api/comercios
```
**Respuesta:**
```json
[
  {
    "idComercio": 1,
    "nomComercio": "Car Center",
    "aforoMaximo": 50
  },
  {
    "idComercio": 2, 
    "nomComercio": "Centro Diseño",
    "aforoMaximo": 30
  }
]
```

### 2. Obtener Servicios por Comercio (para dropdown)
```http
GET /api/servicios/comercio/{idComercio}
```
**Ejemplo:** `GET /api/servicios/comercio/1`

**Respuesta:**
```json
[
  {
    "idServicio": 1,
    "nomServicio": "Lavado General",
    "horaApertura": "09:00:00",
    "horaCierre": "18:00:00",
    "duracion": 30
  },
  {
    "idServicio": 2,
    "nomServicio": "Diseño Cocina", 
    "horaApertura": "08:00:00",
    "horaCierre": "17:00:00",
    "duracion": 120
  }
]
```

### 3. Generar Turnos (botón "Generar")
```http
POST /api/turnos/generar
Content-Type: application/json
```
**Cuerpo de la petición:**
```json
{
  "fechaInicio": "2024-10-10",
  "fechaFin": "2024-10-15",
  "idServicio": 1
}
```

**Respuesta exitosa:**
```json
{
  "mensaje": "Turnos generados exitosamente",
  "cantidadTurnos": 25,
  "turnos": [
    {
      "idTurno": 1,
      "idServicio": 1,
      "fechaTurno": "2024-10-10",
      "horaInicio": "09:00:00",
      "horaFin": "09:30:00",
      "estado": "DISPONIBLE"
    }
  ]
}
```

### 4. Obtener Turnos (para mostrar en tabla)
```http
GET /api/turnos/servicio/{idServicio}?fechaInicio=2024-10-10&fechaFin=2024-10-15
```

**Respuesta:**
```json
[
  {
    "idTurno": 1,
    "idServicio": 1,
    "fechaTurno": "2024-10-10",
    "horaInicio": "09:00:00",
    "horaFin": "09:30:00",
    "estado": "DISPONIBLE",
    "servicio": {
      "nomServicio": "Lavado General",
      "comercio": {
        "nomComercio": "Car Center"
      }
    }
  }
]
```

## Flujo de la página:

1. **Cargar página:** `GET /api/comercios` para llenar dropdown de comercios
2. **Seleccionar comercio:** `GET /api/servicios/comercio/{id}` para llenar dropdown de servicios
3. **Completar fechas y hacer clic en "Generar":** `POST /api/turnos/generar`
4. **Mostrar tabla:** `GET /api/turnos/servicio/{id}` para mostrar los turnos generados

## Configuración

- Todas las rutas tienen habilitado CORS con `@CrossOrigin(origins = "*")`
- Base URL: `http://localhost:8080/api`
- Las fechas deben enviarse en formato ISO: `YYYY-MM-DD`
- Las horas se devuelven en formato: `HH:mm:ss`
