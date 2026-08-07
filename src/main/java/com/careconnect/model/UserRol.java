package com.careconnect.model;

import com.careconnect.model.enums.RolTipo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRol extends BaseAuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolTipo rol;

    @Column(nullable = false)
    private boolean activo;
}