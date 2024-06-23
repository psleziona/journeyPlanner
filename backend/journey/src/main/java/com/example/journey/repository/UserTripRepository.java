package com.example.journey.repository;

import com.example.journey.model.UserTrip;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserTripRepository extends ReactiveCrudRepository<UserTrip, Long> {
    Flux<UserTrip> findAllByIdTrip(Long idTrip);
    Flux<UserTrip> findAllByIdUser(Long idUser);
    Mono<Void> deleteByIdTripAndIdUser(Long idTrip, Long idUser);
}