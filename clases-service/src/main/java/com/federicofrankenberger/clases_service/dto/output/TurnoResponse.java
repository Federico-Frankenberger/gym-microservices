package com.federicofrankenberger.clases_service.dto.output;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TurnoResponse {
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private int cupoMaximo;
    private int cupoDisponible;
    private ProfesorResponse profesor;
    private ClaseResponse clase;
}
