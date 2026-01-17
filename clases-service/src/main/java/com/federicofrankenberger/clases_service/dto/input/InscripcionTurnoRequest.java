package com.federicofrankenberger.clases_service.dto.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InscripcionTurnoRequest {
    private Long turnoId;
    private Long alumnoId;
}
