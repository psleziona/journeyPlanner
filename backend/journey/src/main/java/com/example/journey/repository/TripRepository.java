package com.example.journey.repository;

import com.example.journey.model.Trip;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TripRepository extends ReactiveCrudRepository<Trip, Long> {
    Flux<Trip> findAllByIdOwner(Long userId);
}