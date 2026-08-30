package com.careconnect.service.impl;

import com.careconnect.dto.auth.AuthResponseDTO;
import com.careconnect.dto.auth.LoginRequestDTO;
import com.careconnect.dto.auth.RegistroUsuarioDTO;
import com.careconnect.model.*;
import com.careconnect.model.enums.EstadoUsuario;
import com.careconnect.repository.UsuarioRepository;
import com.careconnect.service.JwtService;
import com.careconnect.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository, 
            JwtService jwtService, 
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO registrar(RegistroUsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya se encuentra registrado");
        }

        // 1. Normalizar el rol
        String rol = (dto.getRol() != null && !dto.getRol().isBlank()) 
                ? dto.getRol().trim().toUpperCase() 
                : "FAMILIAR";

        // 2. Bloqueo de escalada de privilegios
        if ("ADMIN".equals(rol) || "ADMINISTRADOR".equals(rol)) {
            throw new RuntimeException("No está permitido registrarse con rol de Administrador");
        }

        // 3. Creación exclusiva de entidades permitidas
        Usuario usuario;
        switch (rol) {
            case "CUIDADOR" -> usuario = new Cuidador();
            case "ENFERMERO" -> {
                Enfermero enfermero = new Enfermero();
                String matricula = (dto.getMatriculaProfesional() != null && !dto.getMatriculaProfesional().isBlank())
                        ? dto.getMatriculaProfesional()
                        : "MAT-PENDIENTE";
                enfermero.setMatriculaProfesional(matricula);
                usuario = enfermero;
            }
            case "FAMILIAR" -> usuario = new Familiar();
            default -> throw new RuntimeException("Rol no válido: " + rol);
        }

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rol);
        usuario.setEstadoUser(EstadoUsuario.ACTIVO);
        usuario.setEmailVerificado(true);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuarioGuardado);

        return AuthResponseDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .email(usuarioGuardado.getEmail())
                .rol(usuarioGuardado.getRol())
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario);

        return AuthResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .token(token)
                .build();
    }
    @Override
    public AuthResponseDTO obtenerPerfilPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        return AuthResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
    }
}