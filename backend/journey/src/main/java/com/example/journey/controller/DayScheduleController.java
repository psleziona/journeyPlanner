package com.example.journey.controller;

import com.example.journey.model.DaySchedule;
import com.example.journey.model.DayScheduleTask;
import com.example.journey.repository.DayScheduleRepository;
import com.example.journey.service.DayScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/schedules")
@AllArgsConstructor
@CrossOrigin
public class DayScheduleController {
    private DayScheduleService dayScheduleService;

    @GetMapping("/{id}")
    public Mono<DaySchedule> getScheduleById(@PathVariable Long id) {
        return dayScheduleService.findById(id);
    }

    @GetMapping("/trip/{id}")
    public Flux<DaySchedule> getScheduleByTripId(@PathVariable Long id) {
        return dayScheduleService.findByTripId(id);
    }

    @PostMapping("/task/add")
    public Mono<DayScheduleTask> addTaskToSchedule(@RequestBody DayScheduleTask dayScheduleTask) {
        return dayScheduleService.addTaskToSchedule(dayScheduleTask);
    }
}
