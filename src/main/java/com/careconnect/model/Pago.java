package com.careconnect.model;

import com.careconnect.model.enums.EstadoPago;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago extends BaseAuditableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turno_id", nullable = false, unique = true)
    private Turno turno;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(length = 10, nullable = false)
    private String moneda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado;

    @Column(length = 50)
    private String metodo;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "external_status")
    private String externalStatus;

    private Integer intentos;

    @Column(name = "reembolsado_en")
    private LocalDateTime reembolsadoEn;

    private String comprobante;

    private Boolean reembolso;
}