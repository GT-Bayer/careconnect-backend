package com.careconnect.service.impl;

import com.careconnect.dto.adultoMayor.AdultoMayorRequestDTO;
import com.careconnect.dto.adultoMayor.AdultoMayorResponseDTO;

import java.util.List;


public interface AdultoMayorService {
    
    AdultoMayorResponseDTO crear(AdultoMayorRequestDTO dto); 

    AdultoMayorResponseDTO buscar(Long id); 

    List<AdultoMayorResponseDTO> buscarTodos(); 

    AdultoMayorResponseDTO actualizar(Long id, AdultoMayorRequestDTO dto); 
    
    void eliminar(Long id); 
}
