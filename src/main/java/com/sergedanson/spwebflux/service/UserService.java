package com.sergedanson.spwebflux.service;

import com.sergedanson.spwebflux.model.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
   Mono<UserEntity> findUserEntityByEmail(String email);
   Flux<UserEntity> findUsers();
}
