package com.example.journey.service;

import com.example.journey.auth.AuthService;
import com.example.journey.model.*;
import com.example.journey.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final AuthService authService;
    private final DayScheduleRepository dayScheduleRepository;
    private final TripImageRepository tripImageRepository;
    private final UserTripRepository userTripRepository;
    private final TripCommentRepository tripCommentRepository;
    private final UserRepository userRepository;

    public Mono<Trip> findById(Long id) {
        return tripRepository.findById(id)
                .flatMap(trip ->
                        Flux.merge(
                                dayScheduleRepository.findAllByIdTrip(id).collectList().map(daySchedules -> {
                                    trip.setSchedules(daySchedules);
                                    return daySchedules;
                                }),
                                tripImageRepository.findAllByIdTrip(id).collectList().map(tripImages -> {
                                    trip.setImages(tripImages.stream().map(TripImage::getFilename).toList());
                                    return tripImages;
                                }),
                                userTripRepository.findAllByIdTrip(id)
                                        .flatMap(userTrip -> userRepository.findById(userTrip.getIdUser()))
                                        .collectList()
                                        .map(users -> {
                                            trip.setUsers(users);
                                            return users;
                                        }),
                                tripCommentRepository.findAllByIdTrip(id).collectList().map(tripComments -> {
                                    trip.setComments(tripComments.stream().map(TripComment::getComment).toList());
                                    return tripComments;
                                })
                        ).then(Mono.just(trip))
                );
    }

    public Flux<Trip> getAllUserTrips() {
        return authService.getCurrentUser()
                .flatMapMany(user -> {
                    Flux<Trip> userOwnerTrips = tripRepository.findAllByIdOwner(user.getId());
                    Flux<Trip> userTrips = userTripRepository.findAllByIdUser(user.getId())
                            .flatMap(userTrip -> tripRepository.findById(userTrip.getIdTrip()));
                    return Flux.merge(userOwnerTrips, userTrips);
                });
    }

    public Mono<Trip> save(Trip trip) {
        return authService.getCurrentUser()
                .flatMap(user -> {
                    trip.setIdOwner(user.getId());
                    return tripRepository.save(trip)
                            .flatMap(this::createDayScheduleForTrip);
                });
    }

    private Mono<Trip> createDayScheduleForTrip(Trip trip) {
        LocalDate start = trip.getStart();
        LocalDate finish = trip.getFinish();
        return Flux.fromStream(start.datesUntil(finish.plusDays(1)))
                .flatMap(date -> {
                    DaySchedule daySchedule = new DaySchedule();
                    daySchedule.setIdTrip(trip.getId());
                    daySchedule.setDate(date);
                    return dayScheduleRepository.save(daySchedule);
                })
                .then(Mono.just(trip));
    }

    public Mono<Void> addUserToTrip(Long idTrip, Long idUser) {
        return Mono.zip(tripRepository.findById(idTrip), userRepository.findById(idUser))
                .flatMap(tuple -> {
                    Trip trip = tuple.getT1();
                    User user = tuple.getT2();

                    UserTrip userTrip = new UserTrip(user.getId(), trip.getId());

                    return userTripRepository.save(userTrip)
                            .then(Mono.empty());
                });
    }

    public Mono<Void> deleteUserFromTrip(Long idTrip, Long idUser) {
        return userTripRepository.deleteByIdTripAndIdUser(idTrip, idUser);
    }

    public Mono<Void> deleteById(Long id) {
        return tripRepository.deleteById(id);
    }

    public Mono<TripComment> addComment(TripComment tripComment) {
        return authService.getCurrentUser()
                .flatMap(user -> {
                    tripComment.setIdUser(user.getId());
                    return tripCommentRepository.save(tripComment);
                });
    }
}