-- =============================================================================
-- CARECONNECT BACKEND - MIGRACIÓN DEFINITIVA DDL (FLYWAY V1)
-- =============================================================================

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 1. TABLA BASE USUARIOS
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellido` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dni` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rol` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_actualizacion` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `email_verificado` bit(1) NOT NULL,
  `estado_user` enum('ACTIVO','SUSPENDIDO','ELIMINADO','PENDIENTE_VERIFICACION') COLLATE utf8mb4_unicode_ci NOT NULL,
  `foto_perfil` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `dni` (`dni`),
  KEY `idx_usuarios_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. TABLA ADMINISTRADORES
DROP TABLE IF EXISTS `administradores`;
CREATE TABLE `administradores` (
  `usuario_id` bigint NOT NULL,
  `nivel_acceso` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `departamento` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  CONSTRAINT `fk_admin_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. TABLA CUIDADORES
DROP TABLE IF EXISTS `cuidadores`;
CREATE TABLE `cuidadores` (
  `id` bigint NOT NULL,
  `experiencia_anios` int DEFAULT '0',
  `biografia` text COLLATE utf8mb4_unicode_ci,
  `tarifa_hora` decimal(10,2) DEFAULT NULL,
  `zona_cobertura` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `disponible` tinyint(1) NOT NULL DEFAULT '1',
  `anios_experiencia` int DEFAULT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci,
  `precio_hora` decimal(10,2) DEFAULT NULL,
  `zona_principal` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeii6xa1q25adfw4b5t3ax3ssl` (`usuario_id`),
  CONSTRAINT `fk_cuidador_usuario` FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKeii6xa1q25adfw4b5t3ax3ssl` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. TABLA FAMILIARES
DROP TABLE IF EXISTS `familiares`;
CREATE TABLE `familiares` (
  `id` bigint NOT NULL,
  `direccion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contacto_emergencia` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zona` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqojo88t07obl6kc9mufn54wb1` (`usuario_id`),
  CONSTRAINT `fk_familiar_usuario` FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKqojo88t07obl6kc9mufn54wb1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. TABLA ADULTOS MAYORES
DROP TABLE IF EXISTS `adultos_mayores`;
CREATE TABLE `adultos_mayores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `familiar_id` bigint NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellido` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `ficha_medica` text COLLATE utf8mb4_unicode_ci,
  `requerimientos_especiales` text COLLATE utf8mb4_unicode_ci,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `movilidad` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `observaciones` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `fk_adulto_familiar` (`familiar_id`),
  CONSTRAINT `fk_adulto_familiar` FOREIGN KEY (`familiar_id`) REFERENCES `familiares` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. TABLAS AUXILIARES Y CATÁLOGOS
DROP TABLE IF EXISTS `cancelado_por`;
CREATE TABLE `cancelado_por` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `rol_tipo` enum('CUIDADOR','ENFERMERO','FAMILIAR','ADMINISTRADOR') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `certificaciones`;
CREATE TABLE `certificaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `url_certificado` varchar(255) NOT NULL,
  `valido_hasta` datetime(6) DEFAULT NULL,
  `cuidador_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `especialidades`;
CREATE TABLE `especialidades` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `nom_especialidad` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `zonas`;
CREATE TABLE `zonas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `zona` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `cuidador_especialidades`;
CREATE TABLE `cuidador_especialidades` (
  `cuidador_id` bigint NOT NULL,
  `especialidad_id` bigint NOT NULL,
  KEY `FK5occclmdwrvrkxox707pk6o51` (`especialidad_id`),
  CONSTRAINT `FK5occclmdwrvrkxox707pk6o51` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `cuidador_zonas`;
CREATE TABLE `cuidador_zonas` (
  `cuidador_id` bigint NOT NULL,
  `zona_id` bigint NOT NULL,
  KEY `FKb7lujqcwmdib7q5wumeehx1wx` (`zona_id`),
  CONSTRAINT `FKb7lujqcwmdib7q5wumeehx1wx` FOREIGN KEY (`zona_id`) REFERENCES `zonas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `enfermeros`;
CREATE TABLE `enfermeros` (
  `anios_experiencia` int DEFAULT NULL,
  `descripcion` text,
  `matricula_profesional` varchar(50) NOT NULL,
  `precio_hora` decimal(10,2) DEFAULT NULL,
  `visible` bit(1) NOT NULL,
  `zona_principal` varchar(255) DEFAULT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `UK_8gqf5ky9ruvgymg6ny0g582cy` (`matricula_profesional`),
  CONSTRAINT `FKlp2ebjh5f6ie9y1db4823fx29` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `tipos`;
CREATE TABLE `tipos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `nombre_tipo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `tipos_servicio`;
CREATE TABLE `tipos_servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `descripcion_servicio` varchar(255) DEFAULT NULL,
  `nombre_servicio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 7. TABLA TURNOS
DROP TABLE IF EXISTS `turnos`;
CREATE TABLE `turnos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cuidador_id` bigint NOT NULL,
  `familiar_id` bigint NOT NULL,
  `adulto_mayor_id` bigint NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime NOT NULL,
  `estado` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDIENTE',
  `monto_total` decimal(10,2) NOT NULL,
  `observaciones` text COLLATE utf8mb4_unicode_ci,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `descripcion_servicio` text COLLATE utf8mb4_unicode_ci,
  `duracion_minutos` int DEFAULT NULL,
  `estado_turno` enum('PENDIENTE','CONFIRMADO','EN_CURSO','FINALIZADO','CANCELADO') COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha` date NOT NULL,
  `hora_fin` time(6) NOT NULL,
  `hora_inicio` time(6) NOT NULL,
  `motivo_cancelacion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `precio_total` decimal(10,2) NOT NULL,
  `timestamp_inicio` datetime(6) DEFAULT NULL,
  `cancelado_por_id` bigint DEFAULT NULL,
  `tipo_servicio_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_turno_cuidador` (`cuidador_id`),
  KEY `fk_turno_familiar` (`familiar_id`),
  KEY `fk_turno_adulto` (`adulto_mayor_id`),
  KEY `idx_turnos_estado` (`estado`),
  KEY `idx_turnos_fechas` (`fecha_inicio`,`fecha_fin`),
  KEY `FK2wpg8gy3qea8xf8x2ev61x6ti` (`cancelado_por_id`),
  KEY `FKaqi5favb3o3tx363gdu4b7ji0` (`tipo_servicio_id`),
  CONSTRAINT `FK2wpg8gy3qea8xf8x2ev61x6ti` FOREIGN KEY (`cancelado_por_id`) REFERENCES `cancelado_por` (`id`),
  CONSTRAINT `fk_turno_adulto` FOREIGN KEY (`adulto_mayor_id`) REFERENCES `adultos_mayores` (`id`),
  CONSTRAINT `fk_turno_cuidador` FOREIGN KEY (`cuidador_id`) REFERENCES `cuidadores` (`id`),
  CONSTRAINT `fk_turno_familiar` FOREIGN KEY (`familiar_id`) REFERENCES `familiares` (`id`),
  CONSTRAINT `FKaqi5favb3o3tx363gdu4b7ji0` FOREIGN KEY (`tipo_servicio_id`) REFERENCES `tipos_servicio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8. TABLA PAGOS
DROP TABLE IF EXISTS `pagos`;
CREATE TABLE `pagos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `turno_id` bigint NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `metodo_pago` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO','REEMBOLSADO') COLLATE utf8mb4_unicode_ci NOT NULL,
  `transaccion_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `comprobante` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `external_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `external_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `intentos` int DEFAULT NULL,
  `metodo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `moneda` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reembolsado_en` datetime(6) DEFAULT NULL,
  `reembolso` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `turno_id` (`turno_id`),
  CONSTRAINT `fk_pago_turno` FOREIGN KEY (`turno_id`) REFERENCES `turnos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 9. TABLAS DE RESEÑAS Y ROLES
DROP TABLE IF EXISTS `resenas`;
CREATE TABLE `resenas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `turno_id` bigint NOT NULL,
  `puntuacion` int NOT NULL,
  `comentario` text COLLATE utf8mb4_unicode_ci,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `turno_id` (`turno_id`),
  CONSTRAINT `fk_resena_turno` FOREIGN KEY (`turno_id`) REFERENCES `turnos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `resenas_chk_1` CHECK ((`puntuacion` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `resenias`;
CREATE TABLE `resenias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `comentario` text,
  `puntuacion` int NOT NULL,
  `reportada` bit(1) NOT NULL,
  `visible` bit(1) NOT NULL,
  `autor_id` bigint NOT NULL,
  `cuidador_id` bigint NOT NULL,
  `turno_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmclmu2lec50ulm1gdxxyq1w5t` (`autor_id`),
  KEY `FKe1ecsejeyj0muq0298a31jrbt` (`cuidador_id`),
  KEY `FKrtauqppn7tnvjflx36m2u3ffn` (`turno_id`),
  CONSTRAINT `FKe1ecsejeyj0muq0298a31jrbt` FOREIGN KEY (`cuidador_id`) REFERENCES `cuidadores` (`usuario_id`),
  CONSTRAINT `FKmclmu2lec50ulm1gdxxyq1w5t` FOREIGN KEY (`autor_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKrtauqppn7tnvjflx36m2u3ffn` FOREIGN KEY (`turno_id`) REFERENCES `turnos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `rol` enum('CUIDADOR','ENFERMERO','FAMILIAR','ADMINISTRADOR') NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjjnojm0w0eu02ekd6q1fcurms` (`usuario_id`),
  CONSTRAINT `FKjjnojm0w0eu02ekd6q1fcurms` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;