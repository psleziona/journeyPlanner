package com.example.journey.controller;

import com.example.journey.dto.UserTripImagesDTO;
import com.example.journey.model.User;
import com.example.journey.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/images")
    public Flux<UserTripImagesDTO> getUserPhotos() {
        return userService.getUserPhotos();
    }
}