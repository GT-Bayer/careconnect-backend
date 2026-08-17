package com.careconnect.service.impl;

import com.careconnect.dto.adultoMayor.AdultoMayorRequestDTO;
import com.careconnect.dto.adultoMayor.AdultoMayorResponseDTO;
import com.careconnect.exception.ResourceNotFoundException;
import com.careconnect.model.AdultoMayor;
import com.careconnect.model.Familiar;
import com.careconnect.repository.AdultoMayorRepository;
import com.careconnect.repository.FamiliarRepository;
import com.careconnect.service.AdultoMayorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdultoMayorServiceImpl implements AdultoMayorService {

    private final AdultoMayorRepository adultoMayorRepository;
    private final FamiliarRepository familiarRepository;

    @Override
    @Transactional
    public AdultoMayorResponseDTO crear(AdultoMayorRequestDTO dto) {
        Familiar familiar = familiarRepository.findById(dto.getFamiliarId())
                .orElseThrow(() -> new ResourceNotFoundException("Familiar no encontrado con ID: " + dto.getFamiliarId()));

        AdultoMayor adultoMayor = AdultoMayor.builder()
                .familiar(familiar)
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .fechaNacimiento(dto.getFechaNacimiento())
                .observaciones(dto.getObservaciones())
                .movilidad(dto.getMovilidad())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();

        AdultoMayor guardado = adultoMayorRepository.save(adultoMayor);
        return mapToDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public AdultoMayorResponseDTO buscar(Long id) {
        AdultoMayor adultoMayor = adultoMayorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adulto mayor no encontrado con ID: " + id));
        return mapToDTO(adultoMayor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdultoMayorResponseDTO> buscarTodos() {
        return adultoMayorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdultoMayorResponseDTO actualizar(Long id, AdultoMayorRequestDTO dto) {
        AdultoMayor adultoMayor = adultoMayorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adulto mayor no encontrado con ID: " + id));

        if (dto.getFamiliarId() != null && !dto.getFamiliarId().equals(adultoMayor.getFamiliar().getId())) {
            Familiar nuevoFamiliar = familiarRepository.findById(dto.getFamiliarId())
                    .orElseThrow(() -> new ResourceNotFoundException("Familiar no encontrado con ID: " + dto.getFamiliarId()));
            adultoMayor.setFamiliar(nuevoFamiliar);
        }

        adultoMayor.setNombre(dto.getNombre());
        adultoMayor.setApellido(dto.getApellido());
        adultoMayor.setDni(dto.getDni());
        adultoMayor.setFechaNacimiento(dto.getFechaNacimiento());
        adultoMayor.setObservaciones(dto.getObservaciones());
        adultoMayor.setMovilidad(dto.getMovilidad());
        if (dto.getActivo() != null) {
            adultoMayor.setActivo(dto.getActivo());
        }

        AdultoMayor actualizado = adultoMayorRepository.save(adultoMayor);
        return mapToDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!adultoMayorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adulto mayor no encontrado con ID: " + id);
        }
        adultoMayorRepository.deleteById(id);
    }

    private AdultoMayorResponseDTO mapToDTO(AdultoMayor model) {
        return AdultoMayorResponseDTO.builder()
                .id(model.getId())
                .familiarId(model.getFamiliar() != null ? model.getFamiliar().getId() : null)
                .nombre(model.getNombre())
                .apellido(model.getApellido())
                .dni(model.getDni())
                .fechaNacimiento(model.getFechaNacimiento())
                .observaciones(model.getObservaciones())
                .movilidad(model.getMovilidad())
                .activo(model.isActivo())
                .build();
    }
}