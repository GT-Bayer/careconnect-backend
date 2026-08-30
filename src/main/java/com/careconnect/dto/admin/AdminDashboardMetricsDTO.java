package com.careconnect.dto.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDashboardMetricsDTO {
    private long totalUsuarios;
    private long pendientesVerificacion;
    private long usuariosActivos;
    private long usuariosSuspendidos;
}