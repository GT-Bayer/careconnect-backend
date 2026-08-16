package com.careconnect.dto.enfermero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnfermeroBusquedaRequestDTO {

    private String zonaPrincipal;
    private BigDecimal precioMin;
    private BigDecimal precioMax;
    private Boolean visible;
}