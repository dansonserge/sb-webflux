package com.sergedanson.spwebflux.repository;

import com.sergedanson.spwebflux.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserEntityRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<UserEntity> findUserEntityByEmail(String email);
}
