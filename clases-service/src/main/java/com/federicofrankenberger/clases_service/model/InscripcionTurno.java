package com.federicofrankenberger.clases_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "inscripciones",
    uniqueConstraints = {
    @UniqueConstraint(columnNames = {"turno_id", "idAlumno"})
    }
)
public class InscripcionTurno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne (optional = false)
    @JoinColumn(name = "turno_id",nullable=false)
    private Turno turno;

    @Column(nullable=false)
    private Long idAlumno;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private EstadoInscripcion estado;

    @Column(nullable=false)
    private LocalDateTime fechaInscripcion;


}
