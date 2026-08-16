package com.careconnect.dto.cuidador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuidadorBusquedaRequestDTO {

    private Long zonaId;
    private Long especialidadId;
    private BigDecimal precioMin;
    private BigDecimal precioMax;
    private Boolean disponible;
}
