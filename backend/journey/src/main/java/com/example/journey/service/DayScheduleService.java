package com.example.journey.service;

import com.example.journey.auth.AuthService;
import com.example.journey.model.DaySchedule;
import com.example.journey.model.DayScheduleTask;
import com.example.journey.model.Trip;
import com.example.journey.repository.DayScheduleRepository;
import com.example.journey.repository.DayScheduleTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class DayScheduleService {
    private final DayScheduleRepository dayScheduleRepository;
    private final TripService tripService;
    private final AuthService authService;
    private final DayScheduleTaskRepository dayScheduleTaskRepository;

    public Flux<DaySchedule> findByTripId(Long tripId) {
        return tripService.findById(tripId)
                .map(Trip::getSchedules).flatMapMany(Flux::fromIterable);
    }

    public Mono<DaySchedule> findById(Long id) {
        return dayScheduleRepository.findById(id);
    }

    public Mono<DaySchedule> save(DaySchedule daySchedule) {
        return dayScheduleRepository.save(daySchedule);
    }

    public Mono<Void> deleteById(Long id) {
        return dayScheduleRepository.deleteById(id);
    }

    public Mono<DayScheduleTask> addTaskToSchedule(DayScheduleTask dayScheduleTask) {
        return dayScheduleTaskRepository.save(dayScheduleTask);
    }

}
