package com.sergedanson.spwebflux.controller;


import com.sergedanson.spwebflux.dto.AuthRequest;
import com.sergedanson.spwebflux.dto.AuthResponse;
import com.sergedanson.spwebflux.model.UserEntity;
import com.sergedanson.spwebflux.security.JWTUtil;
import com.sergedanson.spwebflux.security.PBKDF2Encoder;
import com.sergedanson.spwebflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PBKDF2Encoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @GetMapping(value ="/users", produces = MediaType. TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserEntity> getAllUsers(){
        return userService.findUsers();
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserEntity> addUser(@RequestBody UserEntity user){
            return userService.createUser(user);
    }


    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findUserEntityByEmail(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
