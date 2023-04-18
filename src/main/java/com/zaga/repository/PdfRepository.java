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

    public List<PdfEntity> viewPdfDocumentByProjectIdAndDocumentType(String projectId, String documentType) {
        List<PdfEntity> details = PdfEntity.list("projectId=?1 and documentType=?2", projectId, documentType);
        return details;
    }

    public PdfEntity viewPdfDocumentByDocumentId(String documentId, String documentType) {
        // System.out.println("----------"+documentId);
        PanacheQuery<PdfEntity> pdf = PdfEntity.find("documentId=?1 and documentType=?2", documentId, documentType);
        PdfEntity pd = pdf.firstResult();
        // System.out.println("------"+pd);
        return pd;
    }

}
