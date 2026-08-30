package com.careconnect.service;

import com.careconnect.dto.auth.AuthResponseDTO;
import com.careconnect.dto.auth.LoginRequestDTO;
import com.careconnect.dto.auth.RegistroUsuarioDTO;
import com.careconnect.model.enums.EstadoUsuario;

public interface UsuarioService {
    
    AuthResponseDTO registrar(RegistroUsuarioDTO dto);
    
    AuthResponseDTO login(LoginRequestDTO dto);

    AuthResponseDTO obtenerPerfilPorEmail(String email);

    void cambiarEstado(Long id, EstadoUsuario nuevoEstado);
}