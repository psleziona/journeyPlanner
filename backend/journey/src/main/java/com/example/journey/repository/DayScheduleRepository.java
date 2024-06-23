package com.example.journey.repository;

import com.example.journey.model.DaySchedule;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface DayScheduleRepository extends ReactiveCrudRepository<DaySchedule, Long> {
    Flux<DaySchedule> findAllByIdTrip(Long idTrip);
}
