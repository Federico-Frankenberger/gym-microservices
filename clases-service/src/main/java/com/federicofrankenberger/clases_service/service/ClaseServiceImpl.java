package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.ClaseCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.ClaseUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.ClaseResponse;
import com.federicofrankenberger.clases_service.exception.BusinessException;
import com.federicofrankenberger.clases_service.exception.DuplicateResourceException;
import com.federicofrankenberger.clases_service.exception.NotFoundException;
import com.federicofrankenberger.clases_service.mapper.Mapper;
import com.federicofrankenberger.clases_service.model.Clase;
import com.federicofrankenberger.clases_service.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClaseServiceImpl implements ClaseService{

    @Autowired
    ClaseRepository repo;


    @Override
    @Transactional
    public ClaseResponse save(ClaseCreateRequest dto) {
        if(repo.existsByNombre(dto.getNombre())) {
            throw  new DuplicateResourceException("Ya existe una clase con el mismo nombre");
        }
        Clase clase = Clase.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .duracionMinutos(dto.getDuracionMinutos())
                .eliminado(false)
                .build();
        return Mapper.toDto(repo.save(clase));

    }

    @Override
    @Transactional
    public ClaseResponse update(Long id, ClaseUpdateRequest dto) {
        Clase claseExistente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Clase no encontrada con ID: " + id));
        Mapper.updateClase(dto, claseExistente);
        return Mapper.toDto(repo.save(claseExistente));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Clase claseExistente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Clase no encontrada con ID: " + id));
        if(claseExistente.isEliminado()) {
            throw new BusinessException("La clase ya se encuentra inactiva.");
        }
        claseExistente.setEliminado(true);
        repo.save(claseExistente);
    }

    @Override
    public ClaseResponse findById(Long id) {
        Clase claseExistente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Clase no encontrada con ID: " + id));
        if(claseExistente.isEliminado()) {
            throw new BusinessException("La clase se encuentra inactiva.");
        }
        return Mapper.toDto(claseExistente);
    }

    @Override
    public List<ClaseResponse> findAll() {
        return repo.findAll().stream().map(Mapper::toDto).toList();
    }
}
