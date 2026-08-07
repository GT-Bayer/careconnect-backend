-- =============================================================================
-- CARECONNECT BACKEND - SCRIPT DE MIGRACIÓN INICIAL (FLYWAY V1)
-- =============================================================================

-- 1. TABLA BASE: USUARIOS
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(30),
    dni VARCHAR(20) UNIQUE,
    rol VARCHAR(30) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. TABLA: CUIDADORES (Hereda/Extiende de Usuarios)
CREATE TABLE IF NOT EXISTS cuidadores (
    id BIGINT PRIMARY KEY,
    experiencia_anios INT DEFAULT 0,
    biografia TEXT,
    tarifa_hora DECIMAL(10, 2),
    zona_cobertura VARCHAR(150),
    disponible BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_cuidador_usuario FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. TABLA: FAMILIARES / SOLICITANTES (Hereda/Extiende de Usuarios)
CREATE TABLE IF NOT EXISTS familiares (
    id BIGINT PRIMARY KEY,
    direccion VARCHAR(255),
    contacto_emergencia VARCHAR(100),
    CONSTRAINT fk_familiar_usuario FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. TABLA: ADULTOS MAYORES (Asociados a un Familiar/Solicitante)
CREATE TABLE IF NOT EXISTS adultos_mayores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    familiar_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20),
    edad INT,
    fecha_nacimiento DATE,
    ficha_medica TEXT,
    requerimientos_especiales TEXT,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_adulto_familiar FOREIGN KEY (familiar_id) REFERENCES familiares(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. TABLA: TURNOS / RESERVAS DE CUIDADO
CREATE TABLE IF NOT EXISTS turnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cuidador_id BIGINT NOT NULL,
    familiar_id BIGINT NOT NULL,
    adulto_mayor_id BIGINT NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    monto_total DECIMAL(10, 2) NOT NULL,
    observaciones TEXT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_turno_cuidador FOREIGN KEY (cuidador_id) REFERENCES cuidadores(id),
    CONSTRAINT fk_turno_familiar FOREIGN KEY (familiar_id) REFERENCES familiares(id),
    CONSTRAINT fk_turno_adulto FOREIGN KEY (adulto_mayor_id) REFERENCES adultos_mayores(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. TABLA: PAGOS
CREATE TABLE IF NOT EXISTS pagos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    turno_id BIGINT NOT NULL UNIQUE,
    monto DECIMAL(10, 2) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    transaccion_id VARCHAR(100),
    fecha_pago DATETIME,
    CONSTRAINT fk_pago_turno FOREIGN KEY (turno_id) REFERENCES turnos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. TABLA: RESEÑAS / CALIFICACIONES
CREATE TABLE IF NOT EXISTS resenas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    turno_id BIGINT NOT NULL UNIQUE,
    puntuacion INT NOT NULL CHECK (puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_resena_turno FOREIGN KEY (turno_id) REFERENCES turnos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8. TABLA: ADMINISTRADORES (Hereda/Extiende de Usuarios)
CREATE TABLE IF NOT EXISTS administradores (
    usuario_id BIGINT PRIMARY KEY,
    nivel_acceso VARCHAR(50),
    departamento VARCHAR(100),
    CONSTRAINT fk_admin_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================================================
-- ÍNDICES PARA OPTIMIZACIÓN DE CONSULTAS
-- =============================================================================
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_turnos_estado ON turnos(estado);
CREATE INDEX idx_turnos_fechas ON turnos(fecha_inicio, fecha_fin);