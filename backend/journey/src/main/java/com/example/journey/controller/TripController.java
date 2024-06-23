package com.example.journey.controller;

import com.example.journey.model.Trip;
import com.example.journey.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
public class TripController {
    private final TripService tripService;

    @GetMapping("/{id}")
    public Mono<Trip> getTripById(@PathVariable Long id) {
        return tripService.findById(id);
    }

    @GetMapping
    public Flux<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @PostMapping
    public Mono<Trip> createTrip(@RequestBody Trip trip) {
        return tripService.save(trip);
    }

    @PostMapping("/user/add/{idTrip}/{idUser}")
    public Mono<Void> addUserToTrip(@PathVariable Long idTrip, @PathVariable Long idUser) {
        return tripService.addUserToTrip(idTrip, idUser);
    }

    @DeleteMapping("/user/delete/{idTrip}/{idUser}")
    public Mono<Void> deleteUserFromTrip(@PathVariable Long idTrip, @PathVariable Long idUser) {
        return tripService.deleteUserFromTrip(idTrip, idUser);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTrip(@PathVariable Long id) {
        return tripService.deleteById(id);
    }
}