package com.careconnect.repository;

import com.careconnect.model.Usuario;
import com.careconnect.model.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByEstadoUser(EstadoUsuario estadoUser);
    
    // Métricas por rol y actividad reciente
    long countByRol(String rol);
    List<Usuario> findTop5ByOrderByIdDesc();
}