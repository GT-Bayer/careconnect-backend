package com.careconnect.dto.adultoMayor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequerimientosCuidadoDTO {
    
    private Long id;
    private String tipoCuidado;
    private String descripcion; 
    private String frecuencia; 
    private String observaciones; 
    
}
