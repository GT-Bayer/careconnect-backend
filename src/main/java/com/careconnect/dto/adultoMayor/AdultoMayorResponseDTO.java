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
    
   private String nombre; 
    private String apellido; 
    private String dni; 
    private String telefono; 
    private String mail; 
    private String direccion; 
    private LocalDate fechaNacimiento;
    private String fotoPerfil; 
    private String codigoPostal; 
    private String provincia;
    private String ciudad;  

}
