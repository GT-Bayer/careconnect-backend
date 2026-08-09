package com.careconnect.service.impl;

import com.careconnect.dto.auth.AuthResponseDTO;
import com.careconnect.dto.auth.LoginRequestDTO;
import com.careconnect.dto.auth.RegistroUsuarioDTO;
import com.careconnect.model.Administrador;
import com.careconnect.model.Usuario;
import com.careconnect.model.enums.EstadoUsuario;
import com.careconnect.repository.UsuarioRepository;
import com.careconnect.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public AuthResponseDTO registrar(RegistroUsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya se encuentra registrado");
        }

        Administrador usuario = new Administrador();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordHash(dto.getPassword());
        
        if (dto.getRol() != null && !dto.getRol().isBlank()) {
            usuario.setRol(dto.getRol());
        } else {
            usuario.setRol("USUARIO");
        }

        usuario.setEstadoUser(EstadoUsuario.ACTIVO);
        usuario.setEmailVerificado(false);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return AuthResponseDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .email(usuarioGuardado.getEmail())
                .rol(usuarioGuardado.getRol())
                .token("dummy-jwt-token")
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!usuario.getPasswordHash().equals(dto.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return AuthResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .token("dummy-jwt-token")
                .build();
    }
}