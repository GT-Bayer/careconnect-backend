package com.careconnect.service;

import com.careconnect.dto.enfermero.EnfermeroBusquedaRequestDTO;
import com.careconnect.dto.enfermero.EnfermeroPerfilResponseDTO;
import com.careconnect.dto.enfermero.EnfermeroPerfilUpdateDTO;

import java.util.List;

public interface EnfermeroService {

    List<EnfermeroPerfilResponseDTO> buscar(EnfermeroBusquedaRequestDTO filtro);

    EnfermeroPerfilResponseDTO obtenerPerfil(Long id);

    EnfermeroPerfilResponseDTO actualizarPerfil(Long id, EnfermeroPerfilUpdateDTO dto);
}
