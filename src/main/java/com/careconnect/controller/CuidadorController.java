package com.careconnect.controller;

import com.careconnect.dto.cuidador.CuidadorBusquedaRequestDTO;
import com.careconnect.dto.cuidador.CuidadorPerfilResponseDTO;
import com.careconnect.dto.cuidador.CuidadorPerfilUpdateDTO;
import com.careconnect.service.CuidadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cuidadores")
public class CuidadorController {

    private final CuidadorService cuidadorService;

    public CuidadorController(CuidadorService cuidadorService) {
        this.cuidadorService = cuidadorService;
    }

    // GET /api/v1/cuidadores?zonaId=1&especialidadId=2&precioMin=1000&precioMax=5000&disponible=true
    @GetMapping
    public ResponseEntity<List<CuidadorPerfilResponseDTO>> buscar(
            @RequestParam(required = false) Long zonaId,
            @RequestParam(required = false) Long especialidadId,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(required = false) Boolean disponible) {

        CuidadorBusquedaRequestDTO filtro = new CuidadorBusquedaRequestDTO(
                zonaId, especialidadId, precioMin, precioMax, disponible);

        return ResponseEntity.ok(cuidadorService.buscar(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuidadorPerfilResponseDTO> obtenerPerfil(@PathVariable Long id) {
        return ResponseEntity.ok(cuidadorService.obtenerPerfil(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuidadorPerfilResponseDTO> actualizarPerfil(
            @PathVariable Long id,
            @Valid @RequestBody CuidadorPerfilUpdateDTO dto) {
        return ResponseEntity.ok(cuidadorService.actualizarPerfil(id, dto));
    }

    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<CuidadorPerfilResponseDTO> cambiarDisponibilidad(
            @PathVariable Long id,
            @RequestParam boolean disponible) {
        return ResponseEntity.ok(cuidadorService.cambiarDisponibilidad(id, disponible));
    }
}