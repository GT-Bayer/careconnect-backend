package com.careconnect.dto.adultoMayor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FichaMedicaDTO {
    private String diagnostico;
    private String medicacion;
    private String alergias;
    private String contactoEmergencia;
}