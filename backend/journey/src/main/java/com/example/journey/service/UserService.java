package com.example.journey.service;

import com.example.journey.auth.AuthService;
import com.example.journey.dto.UserTripImagesDTO;
import com.example.journey.model.User;
import com.example.journey.model.Trip;
import com.example.journey.repository.TripImageRepository;
import com.example.journey.repository.TripRepository;
import com.example.journey.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final TripRepository tripRepository;
    private final TripImageRepository tripImageRepository;

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
                .flatMap(user -> {
                    return tripRepository.findById(user.getId())
                            .flatMap(trip -> {
                                return mapTripToUserTripImagesDTO(trip)
                            });
                });
    }

    private Mono<UserTripImagesDTO> mapTripToUserTripImagesDTO(Trip trip) {
        return tripImageRepository.findById(trip.getId())
                .collectList()
                .map(images -> {
                    List<String> photoFilenames = images.stream()
                            .map(TripImage::getFilename)
                            .collect(Collectors.toList());
                    UserTripImagesDTO dto = new UserTripImagesDTO();
                    dto.setTripName(trip.getName());
                    dto.setPhotos(photoFilenames);
                    return dto;
                });
    }
}