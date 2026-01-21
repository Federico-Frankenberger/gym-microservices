package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.ClaseCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.ClaseUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.ClaseResponse;

public interface ClaseService extends GenericService <ClaseCreateRequest, ClaseUpdateRequest, ClaseResponse,Long>{
}
