package com.federicofrankenberger.clases_service.dto.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProfesorResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String especialidad;
}
