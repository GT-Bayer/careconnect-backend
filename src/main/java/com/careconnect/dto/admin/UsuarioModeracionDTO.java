package com.careconnect.dto.admin;

import com.careconnect.model.enums.EstadoUsuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioModeracionDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol; // Es String según Usuario.java
    private EstadoUsuario estado;
    private String matriculaProfesional;
    private String zonaPrincipal;
}