package com.careconnect.dto.adultoMayor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdultoMayorRequestDTO {
    
    @NotNull(message = "El ID del familiar a cargo es obligatorio")
    private Long familiarId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre; 

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido; 

    @NotBlank(message = "El DNI es obligatorio")
    private String dni; 

    private LocalDate fechaNacimiento;
    private String observaciones;
    private String movilidad;
    private Boolean activo;
}