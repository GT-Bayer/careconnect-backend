package com.careconnect.dto.adultoMayor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdultoMayorResponseDTO {
    private Long id;
    private Long familiarId;
    private String nombre; 
    private String apellido; 
    private String dni; 
    private LocalDate fechaNacimiento;
    private String observaciones;
    private String movilidad;
    private boolean activo;
}