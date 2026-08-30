package com.careconnect.service;

import com.careconnect.dto.admin.AdminDashboardMetricsDTO;
import com.careconnect.dto.admin.UsuarioModeracionDTO;
import com.careconnect.model.Enfermero;
import com.careconnect.model.Usuario;
import com.careconnect.model.enums.EstadoUsuario;
import com.careconnect.repository.EnfermeroRepository;
import com.careconnect.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UsuarioRepository usuarioRepository;

    public AdminService(UsuarioRepository usuarioRepository, EnfermeroRepository enfermeroRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioModeracionDTO> listarUsuariosParaModerar() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            String matricula = null;
            String zona = null;

            if (usuario instanceof Enfermero enf) {
                matricula = enf.getMatriculaProfesional();
                zona = enf.getZonaPrincipal();
            }

            return UsuarioModeracionDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .email(usuario.getEmail())
                    .rol(usuario.getRol())
                    .estado(usuario.getEstadoUser())
                    .matriculaProfesional(matricula)
                    .zonaPrincipal(zona)
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    public void aprobarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.setEstadoUser(EstadoUsuario.ACTIVO);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void suspenderUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.setEstadoUser(EstadoUsuario.SUSPENDIDO);
        usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public AdminDashboardMetricsDTO obtenerMetricas() {
        long total = usuarioRepository.count();
        long pendientes = usuarioRepository.countByEstadoUser(EstadoUsuario.PENDIENTE_VERIFICACION);
        long activos = usuarioRepository.countByEstadoUser(EstadoUsuario.ACTIVO);
        long suspendidos = usuarioRepository.countByEstadoUser(EstadoUsuario.SUSPENDIDO);

        return AdminDashboardMetricsDTO.builder()
                .totalUsuarios(total)
                .pendientesVerificacion(pendientes)
                .usuariosActivos(activos)
                .usuariosSuspendidos(suspendidos)
                .build();
    }
}