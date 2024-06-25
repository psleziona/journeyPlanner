package com.example.journey.repository;

import com.example.journey.model.TripImage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TripImageRepository extends ReactiveCrudRepository<TripImage, Long> {
    Flux<TripImage> findAllByIdTrip(Long idTrip);
    Flux<TripImage> findAllByIdOwner(Long idOwner);
}
