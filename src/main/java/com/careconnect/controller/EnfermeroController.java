package com.careconnect.controller;

import com.careconnect.dto.enfermero.EnfermeroBusquedaRequestDTO;
import com.careconnect.dto.enfermero.EnfermeroPerfilResponseDTO;
import com.careconnect.dto.enfermero.EnfermeroPerfilUpdateDTO;
import com.careconnect.service.EnfermeroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enfermeros")
public class EnfermeroController {

    private final EnfermeroService enfermeroService;

    public EnfermeroController(EnfermeroService enfermeroService) {
        this.enfermeroService = enfermeroService;
    }

    @GetMapping
    public ResponseEntity<List<EnfermeroPerfilResponseDTO>> buscar(
            @RequestParam(required = false) String zonaPrincipal,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(required = false) Boolean visible) {

        EnfermeroBusquedaRequestDTO filtro = new EnfermeroBusquedaRequestDTO(
                zonaPrincipal, precioMin, precioMax, visible);

        return ResponseEntity.ok(enfermeroService.buscar(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnfermeroPerfilResponseDTO> obtenerPerfil(@PathVariable Long id) {
        return ResponseEntity.ok(enfermeroService.obtenerPerfil(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnfermeroPerfilResponseDTO> actualizarPerfil(
            @PathVariable Long id,
            @Valid @RequestBody EnfermeroPerfilUpdateDTO dto) {
        return ResponseEntity.ok(enfermeroService.actualizarPerfil(id, dto));
    }
}