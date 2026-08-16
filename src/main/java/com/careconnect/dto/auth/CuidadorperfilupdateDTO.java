package com.careconnect.dto.cuidador;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

// Todos los campos son opcionales: solo se actualiza lo que venga distinto de null (PATCH-like sobre PUT).
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuidadorPerfilUpdateDTO {

    @Size(max = 2000, message = "La descripción no puede superar los 2000 caracteres")
    private String descripcion;

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer aniosExperiencia;

    private String zonaPrincipal;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio por hora debe ser mayor a 0")
    private BigDecimal precioHora;

    private Boolean disponible;

    private List<Long> especialidadesIds;

    private List<Long> zonasCoberturaIds;
}