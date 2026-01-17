package com.federicofrankenberger.clases_service.dto.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProfesorUpdateRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String especialidad;
}
