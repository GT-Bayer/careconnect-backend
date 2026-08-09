# CareConnect — Backend (API REST)

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot 3.2.5](https://img.shields.io/badge/Spring_Boot-3.2.5-green.svg)](https://spring.io/projects/spring-boot)
[![MySQL 8.0](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Flyway Migration](https://img.shields.io/badge/Flyway-Enabled-red.svg)](https://flywaydb.org/)

API RESTful para la plataforma **CareConnect**, un sistema diseñado para digitalizar, coordinar y auditar la atención de adultos mayores, conectando de forma segura a familiares, cuidadores/enfermeros y administradores.

---

## 👥 Equipo de Desarrollo (Backend)

| Integrante | Rol / Responsabilidad Principales |
| :--- | :--- |
| **Gabriel Tomás Bayer** | Backend, Arquitectura & Base de Datos |
| **Micaela Belén Dominguez** | Backend & Datos |
| **Nahuel Haron** | Backend & Datos |
| **Laia Franco** | Backend |
| **Agostina Ledesma** | Backend |

*(El desarrollo del cliente web/mobile se gestiona de forma desacoplada en el repositorio `careconnect-frontend`).*

---

## 💡 Problema & Solución

### El Problema
La coordinación informal de cuidados domiciliarios genera:
* **Falta de trazabilidad:** Ausencia de registros médicos, historial de atenciones y seguimiento de pacientes.
* **Incertidumbre operativa:** Conflictos de agenda, solapamiento de turnos y falta de validación en la disponibilidad horaria.
* **Riesgo en la contratación:** Falta de verificación de matrículas y antecedentes profesionales.

### La Solución CareConnect
API centralizada para la gestión integral del servicio:
* **Autenticación & RBAC:** Control de acceso basado en roles (*ADMINISTRADOR*, *CUIDADOR*, *ENFERMERO*, *FAMILIAR*).
* **Gestión Horaria & Zonas:** Control de cobertura y agendas dinámicas por franja horaria.
* **Ciclo de Vida de Turnos:** Flujo estricto y concurrente (*PENDIENTE → CONFIRMADO → EN_CURSO → FINALIZADO / CANCELADO*).
* **Ficha Médica:** Centralización de condiciones, recetas, dosis y observaciones médicas.

---

## 🏛️ Arquitectura del Sistema

Implementación de una **Arquitectura en Capas Decoplada (Layered Architecture)** con flujo unidireccional de dependencias:

```text
               [ Cliente Web / Mobile ]
                           │ (HTTP / JSON)
                           ▼
┌────────────────────────────────────────────────────────┐
│ Capa de Presentación (Controllers REST)                │
│ ── Routing, Bean Validation (@Valid), DTOs, Handling   │
└─────────────────────────┬──────────────────────────────┘
                          │
                          ▼
┌────────────────────────────────────────────────────────┐
│ Capa de Negocio (Services)                             │
│ ── Transacciones (@Transactional), Validaciones Domain │
└─────────────────────────┬──────────────────────────────┘
                          │
                          ▼
┌────────────────────────────────────────────────────────┐
│ Capa de Persistencia (Repositories & Storage)          │
│ ── Spring Data JPA, Entidades JPA, MySQL 8.0, Flyway   │
└────────────────────────────────────────────────────────┘
