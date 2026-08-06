package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tipo extends BaseAuditableEntity {

    @Column(name = "nombre_tipo", nullable = false, length = 100)
    private String nombreTipo;
}