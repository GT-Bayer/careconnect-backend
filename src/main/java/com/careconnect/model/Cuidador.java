package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuidadores")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuidador extends Usuario {

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "anios_experiencia")
    private Integer aniosExperiencia;

    @Column(name = "zona_principal")
    private String zonaPrincipal;

    @Column(name = "precio_hora", precision = 10, scale = 2)
    private BigDecimal precioHora;

    @Column(nullable = false)
    private boolean disponible = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuidador_id")
    private List<Certificacion> certificaciones = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "cuidador_especialidades",
        joinColumns = @JoinColumn(name = "cuidador_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private List<Especialidad> especialidades = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "cuidador_zonas",
        joinColumns = @JoinColumn(name = "cuidador_id"),
        inverseJoinColumns = @JoinColumn(name = "zona_id")
    )
    private List<Zona> zonasCobertura = new ArrayList<>();
}