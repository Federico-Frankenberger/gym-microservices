package com.federicofrankenberger.clases_service.mapper;

import com.federicofrankenberger.clases_service.dto.input.InscripcionTurnoRequest;
import com.federicofrankenberger.clases_service.dto.output.InscripcionTurnoResponse;
import com.federicofrankenberger.clases_service.model.InscripcionTurno;
import com.federicofrankenberger.clases_service.model.Turno;

public class InscripcionTurnoMapper {

    // InscripcionTurnoCreateRequest --> InscripcionTurno
    public static InscripcionTurno toEntity (InscripcionTurnoRequest itr, Turno tno) {
        if (itr == null || tno == null) {
            return null;
        }
        return InscripcionTurno.builder()
                .idAlumno(itr.getAlumnoId())
                .turno(tno)
                .build();
    }

    // InscripcionTurno --> InscripcionTurnoResponse
    public static InscripcionTurnoResponse toDto(InscripcionTurno inst){
        if (inst == null) {
            return null;
        }
        return InscripcionTurnoResponse.builder()
                .id(inst.getId())
                .alumnoId(inst.getIdAlumno())
                .estado(inst.getEstado())
                .fechaInscripcion(inst.getFechaInscripcion())
                .turno(TurnoMapper.toDto(inst.getTurno()))
                .build();
    }
}
