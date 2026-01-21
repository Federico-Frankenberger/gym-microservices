package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.InscripcionTurnoRequest;
import com.federicofrankenberger.clases_service.dto.output.InscripcionTurnoResponse;

import java.util.List;

public interface InscripcionTurnoService {

    InscripcionTurnoResponse inscribirse(InscripcionTurnoRequest dto);

    void cancelar(Long inscripcionId);

    void marcarAsistencia(Long inscripcionId);

    void marcarAusente(Long inscripcionId);

    InscripcionTurnoResponse findById(Long id);

    List<InscripcionTurnoResponse> findByTurno(Long turnoId);

    List<InscripcionTurnoResponse> findByAlumno(Long alumnoId);
}
