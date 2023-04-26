package com.zaga.model.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "counter",database = "ProjectManagement")
public class SequenceCounters extends PanacheMongoEntity{

    public String seqName;
    public int seqNumber;
    
}
