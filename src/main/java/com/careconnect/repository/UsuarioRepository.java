package com.careconnect.repository;

import com.careconnect.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método fundamental para el Login
    Optional<Usuario> findByEmail(String email);

    // Método para validar que no haya emails duplicados al registrarse
    boolean existsByEmail(String email);
}