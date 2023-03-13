package com.sergedanson.spwebflux.controller;


import com.sergedanson.spwebflux.model.UserEntity;
import com.sergedanson.spwebflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value ="/users", produces = MediaType. TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserEntity> getAllUsers(){
        return userService.findUsers();
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserEntity> addUser(@RequestBody UserEntity user){
            return userService.createUser(user);
    }

}
