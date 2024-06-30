package com.example.journey.auth;

import com.example.journey.model.User;
import com.example.journey.repository.UserRepository;
import com.example.journey.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Mono<User> getCurrentUser() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Principal::getName)
                .map(Long::parseLong)
                .flatMap(userRepository::findById);
    }

    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userRepository.findByUsernameIs(loginRequest.username())
                .filter(u -> passwordEncoder.matches(loginRequest.password(), u.getPassword()))
                .map(user -> {
                    CustomUserDetails userDetails = new CustomUserDetails(user.getUsername(), user.getPassword(), user.getId());
                    return jwtService.generateToken(Map.of(),userDetails);
                })
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    public Mono<LoginResponse> register(User user) {
        user.setTrips(new ArrayList<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTrips(new ArrayList<>());
        return userRepository.save(user)
                .map(u -> {
                    CustomUserDetails ud = new CustomUserDetails(user.getUsername(), user.getPassword(), user.getId());
                    return jwtService.generateToken(Map.of(),ud);
                })
                .map(LoginResponse::new);
    }
}
