package com.crio.xmeme.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.xmeme.data.MemeEntity;

public interface MemeRepositiry extends MongoRepository<MemeEntity, Integer> {
    
}
