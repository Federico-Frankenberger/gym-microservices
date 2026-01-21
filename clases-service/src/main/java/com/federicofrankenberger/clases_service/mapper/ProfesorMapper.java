package com.federicofrankenberger.clases_service.mapper;

import com.federicofrankenberger.clases_service.dto.input.ProfesorCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.ProfesorUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.ProfesorResponse;
import com.federicofrankenberger.clases_service.model.Profesor;

public class ProfesorMapper {

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
    public static ProfesorResponse toDto(Profesor prof) {
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
}
