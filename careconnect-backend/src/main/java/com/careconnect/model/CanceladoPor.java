package com.careconnect.model;

import com.careconnect.model.enums.RolTipo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cancelado_por")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanceladoPor extends BaseAuditableEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_tipo", nullable = false)
    private RolTipo idRol;
}