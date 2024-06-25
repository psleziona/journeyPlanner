package com.example.journey.auth;

import com.example.journey.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @CrossOrigin
    @PostMapping("/login")
    Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @CrossOrigin
    @PostMapping("/register")
    Mono<User> register(@RequestBody User user) {
        return authService.register(user);
    }

}
