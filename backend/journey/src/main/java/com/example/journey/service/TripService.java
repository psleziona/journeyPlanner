package com.example.journey.service;

import com.example.journey.model.Trip;
import com.example.journey.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TripService {
    private final TripRepository tripRepository;

    public Mono<Trip> findById(Long id) {
        return tripRepository.findById(id);
    }

    public Flux<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Mono<Trip> save(Trip trip) {
        return tripRepository.save(trip);
    }

    public Mono<Void> deleteById(Long id) {
        return tripRepository.deleteById(id);
    }
}