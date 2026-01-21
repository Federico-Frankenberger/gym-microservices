package com.federicofrankenberger.clases_service.dto.output;

import com.federicofrankenberger.clases_service.model.EstadoInscripcion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InscripcionTurnoResponse {
    private Long id;
    private TurnoResponse turno;
    private Long alumnoId;
    private EstadoInscripcion estado;
    private LocalDateTime fechaInscripcion;

}
