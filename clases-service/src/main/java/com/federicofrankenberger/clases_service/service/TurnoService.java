package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.TurnoCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.TurnoUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.TurnoResponse;

public interface TurnoService extends GenericService<TurnoCreateRequest, TurnoUpdateRequest, TurnoResponse, Long> {
}
