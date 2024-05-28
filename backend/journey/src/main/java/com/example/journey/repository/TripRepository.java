package com.example.journey.repository;

import com.example.journey.model.Trip;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TripRepository extends ReactiveCrudRepository<Trip, Long> {
}