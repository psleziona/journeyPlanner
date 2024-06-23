package com.example.journey.repository;

import com.example.journey.model.DayScheduleTask;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface DayScheduleTaskRepository extends ReactiveCrudRepository<DayScheduleTask, Long> {
    Flux<DayScheduleTask> findAllByIdDaySchedule(Long idDaySchedule);
}