package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.PdfEntity;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class PdfRepository implements PanacheMongoRepository<PdfEntity> {

    
    
}
