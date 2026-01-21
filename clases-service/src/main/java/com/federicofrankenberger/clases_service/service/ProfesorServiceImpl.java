package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.ProfesorCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.ProfesorUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.ProfesorResponse;
import com.federicofrankenberger.clases_service.exception.BusinessException;
import com.federicofrankenberger.clases_service.exception.DuplicateResourceException;
import com.federicofrankenberger.clases_service.exception.NotFoundException;
import com.federicofrankenberger.clases_service.mapper.ProfesorMapper;
import com.federicofrankenberger.clases_service.model.Profesor;
import com.federicofrankenberger.clases_service.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository repo;

    @Override
    @Transactional
    public ProfesorResponse save(ProfesorCreateRequest dto) {

        if(repo.existsByDni(dto.getDni())) {
            throw  new DuplicateResourceException("Dni ya existe");
        }
        Profesor profe = Profesor.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .especialidad(dto.getEspecialidad())
                .eliminado(false)
                .build();

        return ProfesorMapper.toDto(repo.save(profe));
    }

    @Override
    @Transactional
    public ProfesorResponse update(Long id, ProfesorUpdateRequest dto) {
        Profesor profesorExistente = repo.findById(id)
                .orElseThrow(()->new NotFoundException("Profesor no encontrado con ID: "+id));

        if(dto.getDni() != null && repo.existsByDniAndIdNot(dto.getDni(),id)){
            throw  new DuplicateResourceException("El DNI ya pertenece a otro profesor");
        }

        ProfesorMapper.updateProfesor(dto, profesorExistente);

        return ProfesorMapper.toDto(repo.save(profesorExistente));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Profesor profesorExistente = repo.findById(id)
                .orElseThrow(()->new NotFoundException("Profesor no encontrado con ID: "+id));
        if (profesorExistente.isEliminado()) {
            throw new BusinessException("El profesor ya se encuentra inactivo.");
        }
        profesorExistente.setEliminado(true);
        repo.save(profesorExistente);
    }

    @Override
    public ProfesorResponse findById(Long id) {
        Profesor profesorExiste = repo.findById(id)
                .orElseThrow(()->new NotFoundException("Profesor no encontrado"));

        if (profesorExiste.isEliminado()) {
            throw new BusinessException("El profesor se encuentra inactivo");
        }

        return ProfesorMapper.toDto(profesorExiste);
    }

    @Override
    public List<ProfesorResponse> findAll() {
        return repo.findAll().stream().map(ProfesorMapper::toDto).toList();
    }
}
