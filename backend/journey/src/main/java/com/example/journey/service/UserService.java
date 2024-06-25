package com.example.journey.service;

import com.example.journey.auth.AuthService;
import com.example.journey.dto.UserTripImagesDTO;
import com.example.journey.model.TripImage;
import com.example.journey.model.User;
import com.example.journey.model.Trip;
import com.example.journey.repository.TripImageRepository;
import com.example.journey.repository.TripRepository;
import com.example.journey.repository.UserRepository;
import com.example.journey.repository.UserTripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final TripRepository tripRepository;
    private final TripImageRepository tripImageRepository;
    private final UserTripRepository userTripRepository;

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    public Flux<UserTripImagesDTO> getUserPhotos() {
        return authService.getCurrentUser()
                .flatMapMany(user -> {
                    Flux<Trip> userOwnerTrips = tripRepository.findAllByIdOwner(user.getId());
                    Flux<Trip> userTrips = userTripRepository.findAllByIdUser(user.getId())
                            .flatMap(userTrip -> tripRepository.findById(userTrip.getIdTrip()));
                    return Flux.merge(userOwnerTrips, userTrips);
                })
                .flatMap(this::mapTripToUserTripImagesDTO);
    }

    private Mono<UserTripImagesDTO> mapTripToUserTripImagesDTO(Trip trip) {
        return tripImageRepository.findAllByIdTrip(trip.getId())
                .collectList()
                .flatMap(tripImages -> {
                    List<String> photos = tripImages.stream().map(TripImage::getFilename).toList();
                    UserTripImagesDTO userTripImagesDTO = new UserTripImagesDTO(trip.getName(), trip.getId(), photos);
                    return Mono.just(userTripImagesDTO);
                });
    }
}