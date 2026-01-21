package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.TurnoCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.TurnoUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.TurnoResponse;
import com.federicofrankenberger.clases_service.exception.BusinessException;
import com.federicofrankenberger.clases_service.mapper.TurnoMapper;
import com.federicofrankenberger.clases_service.model.Clase;
import com.federicofrankenberger.clases_service.model.Profesor;
import com.federicofrankenberger.clases_service.model.Turno;
import com.federicofrankenberger.clases_service.repository.ClaseRepository;
import com.federicofrankenberger.clases_service.repository.ProfesorRepository;
import com.federicofrankenberger.clases_service.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService{

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    @Transactional
    public TurnoResponse save(TurnoCreateRequest dto) {

        Clase clase = claseRepository.findById(dto.getIdClase())
                .orElseThrow(() -> new BusinessException("Clase no encontrada"));

        Profesor profesor = profesorRepository.findById(dto.getIdProfesor())
                .orElseThrow(() -> new BusinessException("Profesor no encontrado"));

        Turno turno = TurnoMapper.toEntity(dto,profesor,clase);

        return TurnoMapper.toDto(turnoRepository.save(turno));
    }

    @Override
    @Transactional
    public TurnoResponse update(Long id, TurnoUpdateRequest dto) {

        Turno turnoExistente = turnoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Turno no encontrado"));

        if (turnoExistente.isEliminado()) {
            throw new BusinessException("El turno se encuentra eliminado");
        }

        if (dto.getCupoMaximo() != null &&
                dto.getCupoMaximo() < turnoExistente.getCupoDisponible()) {
            throw new BusinessException("El cupo máximo no puede ser menor al cupo disponible");
        }

        Clase clase = claseRepository.findById(dto.getIdClase())
                .orElseThrow(() -> new BusinessException("Clase no encontrada"));

        Profesor profesor = profesorRepository.findById(dto.getIdProfesor())
                .orElseThrow(() -> new BusinessException("Profesor no encontrado"));

        TurnoMapper.updateTurno(dto,turnoExistente,clase,profesor);

        return TurnoMapper.toDto(turnoRepository.save(turnoExistente));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Turno turnoExistente = turnoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Turno no encontrado"));
        if (turnoExistente.isEliminado()) {
            throw new BusinessException("El turno ya se encuentra inactivo.");
        }
        turnoExistente.setEliminado(true);
        turnoRepository.save(turnoExistente);
    }

    @Override
    public TurnoResponse findById(Long id) {
        Turno turnoExistente = turnoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Turno no encontrado"));

        if (turnoExistente.isEliminado()) {
            throw new BusinessException("El turno se encuentra eliminado");
        }

        return TurnoMapper.toDto(turnoExistente);
    }

    @Override
    public List<TurnoResponse> findAll() {
        return turnoRepository.findAll().stream().map(TurnoMapper::toDto).toList();
    }
}
