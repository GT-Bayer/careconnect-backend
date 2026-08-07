package com.careconnect.model;

import com.careconnect.model.enums.EstadoTurno;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turno extends BaseAuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familiar_id", nullable = false)
    private Familiar familiar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuidador_id", nullable = false)
    private Cuidador cuidador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adulto_mayor_id", nullable = false)
    private AdultoMayor adultoMayor;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_turno", nullable = false)
    private EstadoTurno estadoTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;

    @Column(name = "descripcion_servicio", columnDefinition = "TEXT")
    private String descripcionServicio;

    @Column(name = "precio_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioTotal;

    @Column(name = "timestamp_inicio")
    private LocalDateTime timestampInicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancelado_por_id")
    private CanceladoPor canceladoPor;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;

    @Column(name = "motivo_cancelacion", length = 255)
    private String motivoCancelacion;
}