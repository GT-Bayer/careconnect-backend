package com.careconnect.service;

import com.careconnect.dto.cuidador.CuidadorBusquedaRequestDTO;
import com.careconnect.dto.cuidador.CuidadorPerfilResponseDTO;
import com.careconnect.dto.cuidador.CuidadorPerfilUpdateDTO;

import java.util.List;

public interface CuidadorService {

    List<CuidadorPerfilResponseDTO> buscar(CuidadorBusquedaRequestDTO filtro);

    CuidadorPerfilResponseDTO obtenerPerfil(Long id);

    CuidadorPerfilResponseDTO actualizarPerfil(Long id, CuidadorPerfilUpdateDTO dto);

    CuidadorPerfilResponseDTO cambiarDisponibilidad(Long id, boolean disponible);
}
