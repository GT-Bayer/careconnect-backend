package com.careconnect.repository;

import com.careconnect.model.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnfermeroRepository extends JpaRepository<Enfermero, Long> {

    Optional<Enfermero> findByMatriculaProfesional(String matriculaProfesional);

    boolean existsByMatriculaProfesional(String matriculaProfesional);

    @Query("""
            SELECT en FROM Enfermero en
            WHERE (:zonaPrincipal IS NULL OR en.zonaPrincipal = :zonaPrincipal)
              AND (:precioMin IS NULL OR en.precioHora >= :precioMin)
              AND (:precioMax IS NULL OR en.precioHora <= :precioMax)
              AND (:visible IS NULL OR en.visible = :visible)
            """)
    List<Enfermero> buscarConFiltros(
            @Param("zonaPrincipal") String zonaPrincipal,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            @Param("visible") Boolean visible
    );
}
