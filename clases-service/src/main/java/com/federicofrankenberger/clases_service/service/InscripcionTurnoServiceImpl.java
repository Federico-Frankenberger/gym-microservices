package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.InscripcionTurnoRequest;
import com.federicofrankenberger.clases_service.dto.output.InscripcionTurnoResponse;
import com.federicofrankenberger.clases_service.exception.BusinessException;
import com.federicofrankenberger.clases_service.exception.NotFoundException;
import com.federicofrankenberger.clases_service.mapper.InscripcionTurnoMapper;
import com.federicofrankenberger.clases_service.model.EstadoInscripcion;
import com.federicofrankenberger.clases_service.model.InscripcionTurno;
import com.federicofrankenberger.clases_service.model.Turno;
import com.federicofrankenberger.clases_service.repository.InscripcionTurnoRepository;
import com.federicofrankenberger.clases_service.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscripcionTurnoServiceImpl implements InscripcionTurnoService {

    @Autowired
    InscripcionTurnoRepository repo;
    @Autowired
    TurnoRepository turnoRepository;

    @Override
    @Transactional
    public InscripcionTurnoResponse inscribirse(InscripcionTurnoRequest dto) {

        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new NotFoundException("Turno no encontrado"));

        if(turno.isEliminado()){
            throw new BusinessException("El turno se encuentra eliminado");
        }

        if (turno.getCupoDisponible() <= 0) {
            throw new BusinessException("No hay cupos disponibles");
        }

        // TODO: validar existencia del alumno cuando exista alumno-service (Feign)
        // alumnoClient.existsById(dto.getAlumnoId())

        if (repo.existsByTurnoIdAndIdAlumno(
                dto.getTurnoId(), dto.getAlumnoId())) {
            throw new BusinessException("El alumno ya está inscripto en este turno");
        }

        InscripcionTurno inscripcion = InscripcionTurno.builder()
                .turno(turno)
                .idAlumno(dto.getAlumnoId())
                .estado(EstadoInscripcion.RESERVADO)
                .fechaInscripcion(LocalDateTime.now())
                .build();
        turno.setCupoDisponible(turno.getCupoDisponible() - 1);

        repo.save(inscripcion);
        turnoRepository.save(turno);

        return InscripcionTurnoMapper.toDto(inscripcion);
    }

    @Override
    @Transactional
    public void cancelar(Long id) {
        InscripcionTurno inscripcion = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));

        if (inscripcion.getEstado() == EstadoInscripcion.CANCELADO) {
            throw new BusinessException("La inscripción ya está cancelada");
        }

        inscripcion.setEstado(EstadoInscripcion.CANCELADO);

        Turno turno = inscripcion.getTurno();
        turno.setCupoDisponible(turno.getCupoDisponible() + 1);

        repo.save(inscripcion);
        turnoRepository.save(turno);
    }

    @Override
    @Transactional
    public void marcarAsistencia(Long id) {
        InscripcionTurno inscripcion = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));

        if (inscripcion.getEstado() != EstadoInscripcion.RESERVADO) {
            throw new BusinessException("Solo se puede marcar asistencia desde estado RESERVADO");
        }

        inscripcion.setEstado(EstadoInscripcion.ASISTIDO);
        repo.save(inscripcion);
    }

    @Override
    @Transactional
    public void marcarAusente(Long id) {
        InscripcionTurno inscripcion = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));

        if (inscripcion.getEstado() != EstadoInscripcion.RESERVADO) {
            throw new BusinessException("Solo se puede marcar ausente desde estado RESERVADO");
        }

        inscripcion.setEstado(EstadoInscripcion.AUSENTE);
        repo.save(inscripcion);
    }

    @Override
    public InscripcionTurnoResponse findById(Long id) {
        InscripcionTurno inscripcion = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));

        return InscripcionTurnoMapper.toDto(inscripcion);
    }

    @Override
    public List<InscripcionTurnoResponse> findByTurno(Long turnoId) {
        if (!turnoRepository.existsById(turnoId)) {
            throw new NotFoundException("Turno no encontrado");
        }

        return repo.findByTurnoId(turnoId).stream().map(InscripcionTurnoMapper::toDto).toList();
    }

    @Override
    public List<InscripcionTurnoResponse> findByAlumno(Long alumnoId) {
        return repo.findByIdAlumno(alumnoId).stream().map(InscripcionTurnoMapper::toDto).toList();
    }
}
