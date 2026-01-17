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
@Table(name="turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable=false)
    private LocalDateTime inicio;

    @Column(nullable=false)
    private LocalDateTime fin;

    @Column(nullable=false)
    private int cupoMaximo;

    @Column(nullable=false)
    private int cupoDisponible;

    @ManyToOne (optional = false)
    @JoinColumn(name = "clase_id",nullable=false)
    private Clase clase;

    @ManyToOne (optional = false)
    @JoinColumn(name = "profesor_id",nullable=false)
    private Profesor profesor;

    @Column(nullable=false)
    private boolean eliminado;

}
