package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zonas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zona extends BaseAuditableEntity {

    @Column(nullable = false, length = 100)
    private String zona;
}