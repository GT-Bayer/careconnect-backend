package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificacion extends BaseAuditableEntity {

    @Column(name = "url_certificado", nullable = false)
    private String urlCertificado;

    private String descripcion;

    @Column(name = "valido_hasta")
    private LocalDateTime validoHasta;

    private boolean activo;
}