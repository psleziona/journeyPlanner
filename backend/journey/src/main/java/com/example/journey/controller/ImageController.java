package com.example.journey.controller;

import com.example.journey.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> uploadImage(@RequestPart("file") FilePart file, @RequestPart("idTrip") String idTrip) throws IOException {
        return imageService.saveImage(file, idTrip);
    }

    @GetMapping("/{filename}")
    public Mono<Resource> getImage(@PathVariable String filename) {
        return imageService.loadImageAsResource(filename)
                .switchIfEmpty(Mono.error(new RuntimeException("Image not found")));
    }

}