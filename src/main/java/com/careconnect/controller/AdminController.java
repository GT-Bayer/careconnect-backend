package com.careconnect.controller;

import com.careconnect.dto.admin.AdminDashboardMetricsDTO;
import com.careconnect.dto.admin.UsuarioModeracionDTO;
import com.careconnect.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')") // Bloqueo estricto 403 para cualquier otro rol o anónimo
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModeracionDTO>> listarUsuarios() {
        return ResponseEntity.ok(adminService.listarUsuariosParaModerar());
    }

    @PatchMapping("/usuarios/{id}/aprobar")
    public ResponseEntity<Void> aprobarUsuario(@PathVariable Long id) {
        adminService.aprobarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/usuarios/{id}/suspender")
    public ResponseEntity<Void> suspenderUsuario(@PathVariable Long id) {
        adminService.suspenderUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/metricas")
    public ResponseEntity<AdminDashboardMetricsDTO> obtenerMetricas() {
        return ResponseEntity.ok(adminService.obtenerMetricas());
    }
}