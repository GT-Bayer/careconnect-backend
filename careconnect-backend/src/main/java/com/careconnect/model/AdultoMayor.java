package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "adultos_mayores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdultoMayor extends BaseAuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familiar_id", nullable = false)
    private Familiar familiar;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 20)
    private String dni;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private String movilidad;

    private boolean activo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
}