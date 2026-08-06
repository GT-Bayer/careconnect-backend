package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "especialidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad extends BaseAuditableEntity {

    @Column(name = "nom_especialidad", nullable = false, length = 100)
    private String nomEspecialidad;
}