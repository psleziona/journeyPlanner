package com.example.journey.auth;

import com.example.journey.model.User;
import com.example.journey.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;
    public Mono<User> getCurrentUser() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Principal::getName)
                .flatMap(userRepository::findByUsernameIs);
    }

    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.username())
                .filter(u -> passwordEncoder.matches(loginRequest.password(), u.getPassword()))
                .map(tokenProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    public Mono<User> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTrips(new ArrayList<>());
        return userRepository.save(user);
    }
}
