package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.SequenceCounters;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class SequenceRepository implements PanacheMongoRepository<SequenceCounters>{


    public String getSquenceCounter (String seqname){
          PanacheQuery <SequenceCounters> count = SequenceCounters.find("seqName = ?1" , seqname);
          SequenceCounters scouter = count.firstResult();

          String seqnumber =   Integer.toString( scouter.getSeqNumber());

          scouter.setSeqNumber(scouter.getSeqNumber()+1);

        SequenceCounters.update(scouter);
        return  seqnumber;
    }
    
}
