package com.federicofrankenberger.clases_service.service;

import java.util.List;

// I (Input/Create), U (Update), O (Output/Response)
public interface GenericService <I,U,O,ID>{
            O save(I dto);
            O update(ID id ,U dto);
            void delete(ID id);
            O findById(ID id);
            List<O> findAll();
}
