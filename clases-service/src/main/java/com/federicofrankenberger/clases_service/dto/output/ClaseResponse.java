package com.federicofrankenberger.clases_service.dto.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClaseResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer duracionMinutos;

}
