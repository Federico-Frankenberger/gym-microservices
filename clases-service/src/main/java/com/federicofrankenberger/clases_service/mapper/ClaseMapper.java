package com.federicofrankenberger.clases_service.mapper;

import com.federicofrankenberger.clases_service.dto.input.ClaseCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.ClaseUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.ClaseResponse;
import com.federicofrankenberger.clases_service.model.Clase;

public class ClaseMapper {

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
}
