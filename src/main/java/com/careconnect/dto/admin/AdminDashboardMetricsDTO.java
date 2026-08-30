package com.careconnect.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardMetricsDTO {
    // Totales por estado
    private long totalUsuarios;
    private long pendientesVerificacion;
    private long usuariosActivos;
    private long usuariosSuspendidos;

    // Desglose por rol
    private long totalCuidadores;
    private long totalEnfermeros;
    private long totalFamiliares;

    // Actividad reciente
    private List<UsuarioModeracionDTO> ultimosRegistros;
}