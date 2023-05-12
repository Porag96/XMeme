package com.crio.xmeme.repository;

import com.crio.xmeme.data.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
}
