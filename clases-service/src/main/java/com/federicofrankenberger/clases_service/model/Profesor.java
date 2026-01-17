package com.federicofrankenberger.clases_service.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String apellido;

    @Column(unique=true,nullable=false)
    private String dni;

    @Column(nullable=false)
    private String especialidad;

    @Column(nullable=false)
    private boolean eliminado;
}
