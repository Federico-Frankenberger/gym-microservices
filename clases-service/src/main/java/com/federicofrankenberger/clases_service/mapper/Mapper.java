package com.federicofrankenberger.clases_service.mapper;

import com.federicofrankenberger.clases_service.dto.input.*;
import com.federicofrankenberger.clases_service.dto.output.ClaseResponse;
import com.federicofrankenberger.clases_service.dto.output.InscripcionTurnoResponse;
import com.federicofrankenberger.clases_service.dto.output.ProfesorResponse;
import com.federicofrankenberger.clases_service.dto.output.TurnoResponse;
import com.federicofrankenberger.clases_service.model.Clase;
import com.federicofrankenberger.clases_service.model.InscripcionTurno;
import com.federicofrankenberger.clases_service.model.Profesor;
import com.federicofrankenberger.clases_service.model.Turno;


public class Mapper {

    // ClaseCreateRequest --> Clase
    public static Clase toEntity(ClaseCreateRequest ccr) {
        if (ccr == null) {
            return null;
        }
        return Clase.builder()
                .nombre(ccr.getNombre())
                .descripcion(ccr.getDescripcion())
                .duracionMinutos(ccr.getDuracionMinutos())
                .eliminado(false)
                .build();
    }

    // ClaseUpdateRequest --> Clase
    public static void updateClase(ClaseUpdateRequest cur, Clase clase) {
        if (cur == null || clase == null) {
            return;
        }
        if (cur.getNombre() != null) {
            clase.setNombre(cur.getNombre());
        }
        if (cur.getDescripcion() != null) {
            clase.setDescripcion(cur.getDescripcion());
        }
        if (cur.getDuracionMinutos() != null) {
            clase.setDuracionMinutos(cur.getDuracionMinutos());
        }
    }

    // Clase --> ClaseResponse
    public static ClaseResponse toDto(Clase cl) {
        if (cl == null) {
            return null;
        }
        return ClaseResponse.builder()
                .id(cl.getId())
                .nombre(cl.getNombre())
                .descripcion(cl.getDescripcion())
                .duracionMinutos(cl.getDuracionMinutos())
                .build();

    }

    // ProfesorCreateRequest --> Profesor
    public static Profesor toEntity(ProfesorCreateRequest prof) {
        if (prof == null) {
            return null;
        }
        return Profesor.builder()
                .nombre(prof.getNombre())
                .apellido(prof.getApellido())
                .dni(prof.getDni())
                .especialidad(prof.getEspecialidad())
                .build();

    }

    // ProfesorUpdateRequest --> Profesor
    public static void updateProfesor (ProfesorUpdateRequest puf, Profesor prof) {
        if (puf == null || prof == null) {
            return;
        }
        if (puf.getNombre() != null) {
            prof.setNombre(puf.getNombre());
        }
        if (puf.getApellido() != null) {
            prof.setApellido(puf.getApellido());
        }
        if (puf.getDni() != null) {
            prof.setDni(puf.getDni());
        }
        if (puf.getEspecialidad() != null) {
            prof.setEspecialidad(puf.getEspecialidad());
        }
    }

    // Profesor --> ProfesorResponse
    public static ProfesorResponse  toDto(Profesor prof) {
        if (prof == null) {
            return null;
        }
        return ProfesorResponse.builder()
                .id(prof.getId())
                .nombre(prof.getNombre())
                .apellido(prof.getApellido())
                .dni(prof.getDni())
                .especialidad(prof.getEspecialidad())
                .build();
    }

    // TurnoCreateRequest --> Turno
    public static Turno toEntity(TurnoCreateRequest tcr,Profesor prof, Clase clase) {
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
    public static void updateTurno (TurnoUpdateRequest puf, Turno turno,Clase clase,Profesor prof) {
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
                .profesor(Mapper.toDto(turno.getProfesor()))
                .clase(Mapper.toDto(turno.getClase()))
                .build();

    }

    // InscripcionTurnoCreateRequest --> InscripcionTurno
    public static InscripcionTurno toEntity (InscripcionTurnoRequest itr,Turno tno) {
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
                .turno(Mapper.toDto(inst.getTurno()))
                .build();
    }
}
