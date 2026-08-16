package com.careconnect.repository;

import com.careconnect.model.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador, Long> {

    // Búsqueda con filtros opcionales (zona, especialidad, rango de precio, disponibilidad).
    // Si un parámetro llega en null, ese filtro se ignora.
    @Query("""
            SELECT DISTINCT c FROM Cuidador c
            LEFT JOIN c.zonasCobertura z
            LEFT JOIN c.especialidades e
            WHERE (:zonaId IS NULL OR z.id = :zonaId)
              AND (:especialidadId IS NULL OR e.id = :especialidadId)
              AND (:precioMin IS NULL OR c.precioHora >= :precioMin)
              AND (:precioMax IS NULL OR c.precioHora <= :precioMax)
              AND (:disponible IS NULL OR c.disponible = :disponible)
            """)
    List<Cuidador> buscarConFiltros(
            @Param("zonaId") Long zonaId,
            @Param("especialidadId") Long especialidadId,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            @Param("disponible") Boolean disponible
    );
}
