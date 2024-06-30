package com.example.journey.service;

import com.example.journey.auth.AuthService;
import com.example.journey.dto.DayScheduleDTO;
import com.example.journey.model.DaySchedule;
import com.example.journey.model.DayScheduleTask;
import com.example.journey.model.Trip;
import com.example.journey.repository.DayScheduleRepository;
import com.example.journey.repository.DayScheduleTaskRepository;
import com.example.journey.repository.TripRepository;
import com.example.journey.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    public Flux<DaySchedule> findByTripId(Long tripId) {
        return tripService.findById(tripId)
                .map(Trip::getSchedules).flatMapMany(Flux::fromIterable);
    }

    public Mono<DayScheduleDTO> findById(Long id) {
        return dayScheduleRepository.findById(id)
                .flatMap(daySchedule -> dayScheduleTaskRepository.findAllByIdDaySchedule(daySchedule.getId())
                        .flatMap(dayScheduleTask -> userRepository.findById(dayScheduleTask.getIdUser())
                                .map(user -> dayScheduleTask.getTask() + " - " + user.getUsername()))
                        .collectList()
                        .flatMap(tasksWithUsername -> tripRepository.findById(daySchedule.getIdTrip())
                                .map(trip -> new DayScheduleDTO(
                                        daySchedule.getId(),
                                        trip.getName(),
                                        daySchedule.getDate(),
                                        tasksWithUsername
                                ))
                        )
                );
    }


    public Mono<Void> deleteById(Long id) {
        return dayScheduleRepository.deleteById(id);
    }

    public Mono<DayScheduleTask> addTaskToSchedule(DayScheduleTask dayScheduleTask) {
        return authService.getCurrentUser()
                .flatMap(user -> {
                    dayScheduleTask.setIdUser(user.getId());
                    return dayScheduleTaskRepository.save(dayScheduleTask);
                });
    }

}
