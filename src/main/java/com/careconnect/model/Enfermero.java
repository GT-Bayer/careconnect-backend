package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "enfermeros")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enfermero extends Usuario {

    @Column(name = "matricula_profesional", nullable = false, unique = true, length = 50)
    private String matriculaProfesional;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "anios_experiencia")
    private Integer aniosExperiencia;

    @Column(name = "zona_principal")
    private String zonaPrincipal;

    @Column(name = "precio_hora", precision = 10, scale = 2)
    private BigDecimal precioHora;

    private boolean visible;
}