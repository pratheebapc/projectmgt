package com.zaga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.PdfEntity;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class PdfRepository implements PanacheMongoRepository<PdfEntity> {

    public List<PdfEntity> viewPdfDocumentByProjectId(String projectId) {
        List<PdfEntity> details = PdfEntity.list("projectId=?1", projectId);
        return details;
    }

    public PdfEntity viewPdfDocumentByDocumentId(String documentId) {
        // System.out.println("----------"+documentId);
        PanacheQuery<PdfEntity> pdf = PdfEntity.find("documentId=?1", documentId);
        PdfEntity pd = pdf.firstResult();
        // System.out.println("------"+pd);
        return pd;
    }

}
