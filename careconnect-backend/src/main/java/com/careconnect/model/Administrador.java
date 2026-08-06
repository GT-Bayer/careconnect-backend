package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Usuario {

    @Column(length = 100)
    private String departamento;
}