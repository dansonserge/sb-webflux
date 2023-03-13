package com.sergedanson.spwebflux.service.impl;

import com.sergedanson.spwebflux.model.UserEntity;
import com.sergedanson.spwebflux.repository.UserEntityRepository;
import com.sergedanson.spwebflux.security.PBKDF2Encoder;
import com.sergedanson.spwebflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PBKDF2Encoder passwordEncoder;

    public Mono<UserEntity> findUserEntityByEmail(String email) {
        return userEntityRepository.findUserEntityByEmail(email).switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    public Flux<UserEntity> findUsers() {
        return userEntityRepository.findAll();
    }

    public Mono<UserEntity> createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntityRepository.save(user);
    }

    public Mono<UserEntity> blockUser(UserEntity user){
        return userEntityRepository.findById(user.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .map(olderUser -> {
                            olderUser.setIsActive(false);
                            return olderUser;
                        })
                .flatMap(userEntityRepository::save);
    }
}
