package com.example.journey.repository;

import com.example.journey.model.TripComment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TripCommentRepository extends ReactiveCrudRepository<TripComment, Long> {
    Flux<TripComment> findAllByIdTrip(Long idTrip);
}