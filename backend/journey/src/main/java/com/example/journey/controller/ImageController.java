package com.example.journey.controller;

import com.example.journey.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> uploadImage(@RequestPart("file") FilePart file) throws IOException {
        return imageService.saveImage(file);
    }

    @GetMapping("/{filename}")
    public Mono<Resource> getImage(@PathVariable String filename) {
        return imageService.loadImageAsResource(filename)
                .switchIfEmpty(Mono.error(new RuntimeException("Image not found")));
    }

}