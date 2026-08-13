package com.careconnect.controller;

import com.careconnect.dto.adultoMayor.AdultoMayorRequestDTO;
import com.careconnect.dto.adultoMayor.AdultoMayorResponseDTO;
import com.careconnect.service.impl.AdultoMayorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adultos-mayores")
public class AdultoMayorController {

    private final AdultoMayorService adultoMayorService;

    @Autowired
    public AdultoMayorController(AdultoMayorService adultoMayorService) {
        this.adultoMayorService = adultoMayorService;
    }

    @PostMapping
    public ResponseEntity<AdultoMayorResponseDTO> crear(@Valid @RequestBody AdultoMayorRequestDTO dto) {
        AdultoMayorResponseDTO response = adultoMayorService.crear(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdultoMayorResponseDTO> buscar(@PathVariable Long id) {
        AdultoMayorResponseDTO response = adultoMayorService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AdultoMayorResponseDTO>> buscarTodos() {
        List<AdultoMayorResponseDTO> response = adultoMayorService.buscarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdultoMayorResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AdultoMayorRequestDTO dto) {
        AdultoMayorResponseDTO response = adultoMayorService.actualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        adultoMayorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}