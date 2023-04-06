package com.zaga.model.entity;

import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id"})
@MongoEntity(collection="pdfs", database="ProjectManagement")
public class PdfEntity extends PanacheMongoEntityBase {

    public ObjectId id;
    String documentId;
    public String name;
    public Binary data;
    public ProjectDetails projectDetails;

}
