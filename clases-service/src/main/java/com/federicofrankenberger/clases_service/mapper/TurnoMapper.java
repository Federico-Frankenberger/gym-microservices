package com.federicofrankenberger.clases_service.mapper;

import com.federicofrankenberger.clases_service.dto.input.TurnoCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.TurnoUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.TurnoResponse;
import com.federicofrankenberger.clases_service.model.Clase;
import com.federicofrankenberger.clases_service.model.Profesor;
import com.federicofrankenberger.clases_service.model.Turno;

public class TurnoMapper {

    // TurnoCreateRequest --> Turno
    public static Turno toEntity(TurnoCreateRequest tcr, Profesor prof, Clase clase) {
        if (tcr == null) {
            return null;
        }
        return Turno.builder()
                .inicio(tcr.getInicio())
                .fin(tcr.getFin())
                .cupoMaximo(tcr.getCupoMaximo())
                .cupoDisponible(tcr.getCupoMaximo())
                .profesor(prof)
                .clase(clase)
                .eliminado(false)
                .build();

    }

    // TurnoUpdateRequest --> Turno
    public static void updateTurno (TurnoUpdateRequest puf, Turno turno, Clase clase, Profesor prof) {
        if (puf == null || turno == null){
            return;
        }

        if (puf.getInicio() != null) {
            turno.setInicio(puf.getInicio());
        }
        if (puf.getFin() != null) {
            turno.setFin(puf.getFin());
        }
        if (puf.getCupoMaximo() != null) {
            turno.setCupoMaximo(puf.getCupoMaximo());
        }
        if (prof != null) {
            turno.setProfesor(prof);
        }
        if (clase != null) {
            turno.setClase(clase);
        }
    }

    // Turno --> TurnoResponse
    public static TurnoResponse toDto(Turno turno){
        if (turno == null) {
            return null;
        }
        return TurnoResponse.builder()
                .id(turno.getId())
                .inicio(turno.getInicio())
                .fin(turno.getFin())
                .cupoMaximo(turno.getCupoMaximo())
                .cupoDisponible(turno.getCupoDisponible())
                .profesor(ProfesorMapper.toDto(turno.getProfesor()))
                .clase(ClaseMapper.toDto(turno.getClase()))
                .build();

    }
}
