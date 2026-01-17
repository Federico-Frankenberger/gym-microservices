package com.federicofrankenberger.clases_service.dto.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClaseCreateRequest {
    private String nombre;
    private String descripcion;
    private Integer duracionMinutos;
}
