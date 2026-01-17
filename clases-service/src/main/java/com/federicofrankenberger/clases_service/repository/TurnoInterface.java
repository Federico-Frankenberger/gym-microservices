package com.federicofrankenberger.clases_service.repository;

import com.federicofrankenberger.clases_service.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoInterface extends JpaRepository<Turno, Long> {
}
