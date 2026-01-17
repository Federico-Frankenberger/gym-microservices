package com.federicofrankenberger.clases_service.dto.input;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TurnoUpdateRequest {
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private int cupoMaximo;
    private Long idProfesor;
    private Long idClase;
}
