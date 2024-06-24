package com.example.journey.service;

import com.example.journey.auth.AuthService;
import com.example.journey.model.TripImage;
import com.example.journey.repository.TripImageRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static final Path IMAGE_DIRECTORY = Paths.get("images");
    private final AuthService authService;
    private final TripImageRepository tripImageRepository;

    public ImageService(AuthService authService, TripImageRepository tripImageRepository) {
        this.authService = authService;
        this.tripImageRepository = tripImageRepository;
        try {
            Files.createDirectories(IMAGE_DIRECTORY);
        } catch (IOException e) {
            throw new RuntimeException("Could not create image directory", e);
        }
    }

    public Mono<String> saveImage(FilePart file, String idTrip) {
        Long id = Long.parseLong(idTrip);
        String extension = getFileExtension(file.filename());
        String fileName = generateFileName(extension);
        Path path = Paths.get(IMAGE_DIRECTORY.toString(), fileName);

        return DataBufferUtils.write(file.content(), path)
                .then(Mono.defer(() -> {
                    try {
                        Files.createDirectories(path.getParent());
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                    return authService.getCurrentUser()
                            .flatMap(user -> {
                                TripImage tripImage = new TripImage(id, user.getId(), fileName);
                                return tripImageRepository.save(tripImage);
                            })
                            .thenReturn(fileName);
                }));
    }

    public Mono<Resource> loadImageAsResource(String fileName) {
        return Mono.fromCallable(() -> {
            try {
                Path filePath = IMAGE_DIRECTORY.resolve(fileName);
                Resource resource = new UrlResource(filePath.toUri());

                if (resource.exists() && resource.isReadable()) {
                    return resource;
                } else {
                    throw new MalformedURLException("File not found or not readable");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Failed to load file", e);
            }
        });
    }

    public String generateFileName(String extension) {
        return RandomStringUtils.randomAlphanumeric(30) + "." + extension;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
