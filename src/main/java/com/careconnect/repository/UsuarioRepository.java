package com.careconnect.repository;

import com.careconnect.model.Usuario;
import com.careconnect.model.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    // Agregado para las métricas del dashboard
    long countByEstadoUser(EstadoUsuario estadoUser);
}