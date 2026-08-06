# CareConnect — Backend (API REST)

API RESTful para la plataforma **CareConnect**, un sistema orientado a digitalizar y coordinar el cuidado de adultos mayores, conectando *familiares*, *cuidadores/enfermeros* y *administradores* con trazabilidad, seguridad e historial operativo.

---

## 📋 Índice

1. [Equipo de Desarrollo](#-equipo-de-desarrollo)
2. [Problema & Solución](#-problema--solución)
3. [Arquitectura del Sistema](#-arquitectura-del-sistema)
4. [Stack Tecnológico](#-stack-tecnológico)
5. [Patrones de Diseño Implementados](#-patrones-de-diseño-implementados)
6. [Modelo de Datos & Persistencia](#-modelo-de-datos--persistencia)
7. [Estrategia de Seguridad](#-estrategia-de-seguridad)
8. [Estructura del Proyecto](#-estructura-del-proyecto)
9. [Flujo de Trabajo en Git](#-flujo-de-trabajo-en-git)
10. [Roadmap](#-roadmap)
11. [Cómo Ejecutar el Proyecto](#-cómo-ejecutar-el-proyecto)

---

## 👥 Equipo de Desarrollo (Backend)

| Integrante | Rol en Backend |
| :--- | :--- |
| **Gabriel Tomás Bayer** | Backend, Arquitectura & Base de Datos |
| **Micaela Belén Dominguez** | Backend & Datos |
| **Nahuel Haron** | Backend & Datos |
| **Laia Franco** | Backend |
| **Agostina Ledesma** | Backend |

*(Nota: El desarrollo de la interfaz de usuario se gestiona en un repositorio independiente `careconnect-frontend`).*

---

## 💡 Problema & Solución

### El Problema
La coordinación del cuidado de adultos mayores suele realizarse mediante canales informales (mensajería instantánea, recomendaciones boca a boca), generando:
- Inexistencia de registros sobre el historial de atenciones y ficha médica.
- Ausencia de validación de disponibilidad y riesgos de doble reserva de turnos.
- Incertidumbre sobre la validación de matrículas y antecedentes profesionales.

### La Solución CareConnect
CareConnect provee una API centralizada que gestiona el ciclo completo del servicio:
- **Gestión de Perfiles & Roles:** Autenticación y autorización diferenciada (Familiar, Cuidador, Enfermero, Administrador).
- **Control de Disponibilidad Horaria:** Definición de agendas semanales por franjas horarias y zonas de cobertura.
- **Gestión de Turnos:** Ciclo de vida estricto (*PENDIENTE → CONFIRMADO → EN_CURSO → FINALIZADO / CANCELADO*) con validación anti-solapamiento.
- **Ficha Médica del Adulto Mayor:** Registro de condiciones médicas, medicamentos, dosis y necesidades de cuidado.

---

## 🏛️ Arquitectura

El sistema está construido sobre una **Arquitectura en Capas Decoplada (Layered Architecture)** con flujo de dependencias unidireccional:

```text
[ Cliente Web / Móvil ]
          │ (Peticiones HTTP / JSON)
          ▼
┌────────────────────────────────────────────────────────┐
│ Capa de Presentación (Controller REST)                │
│ ── Controladores, DTOs, Bean Validation, Exceptions    │
└─────────────────────────┬──────────────────────────────┘
                          │ (Lógica de Negocio)
                          ▼
┌────────────────────────────────────────────────────────┐
│ Capa de Negocio (Service Concreto)                     │
│ ── Clases @Service directas, Transacciones, Reglas     │
└─────────────────────────┬──────────────────────────────┘
                          │ (Operaciones I/O)
                          ▼
┌────────────────────────────────────────────────────────┐
│ Capa de Persistencia (Repository & DB)                 │
│ ── Spring Data JPA, Entidades, MySQL 8.0               │
└────────────────────────────────────────────────────────┘