package com.federicofrankenberger.clases_service.repository;

import com.federicofrankenberger.clases_service.model.InscripcionTurno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionTurnoRepository extends JpaRepository<InscripcionTurno, Long> {
    boolean existsByTurnoIdAndIdAlumno(Long turnoId, Long idAlumno);

    List<InscripcionTurno> findByTurnoId(Long turnoId);

    List<InscripcionTurno> findByIdAlumno(Long idAlumno);
}
