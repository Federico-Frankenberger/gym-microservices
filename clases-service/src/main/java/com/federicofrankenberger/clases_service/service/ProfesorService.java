package com.federicofrankenberger.clases_service.service;

import com.federicofrankenberger.clases_service.dto.input.ProfesorCreateRequest;
import com.federicofrankenberger.clases_service.dto.input.ProfesorUpdateRequest;
import com.federicofrankenberger.clases_service.dto.output.ProfesorResponse;
import com.federicofrankenberger.clases_service.model.Profesor;

import java.util.List;

public interface ProfesorService extends GenericService <ProfesorCreateRequest, ProfesorUpdateRequest, ProfesorResponse,Long>{

}