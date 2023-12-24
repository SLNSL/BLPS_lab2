package ru.ntv.controllers.journalist;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ntv.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("images")
@Validated
public class JournalistImageController {
    private final ImageService imageService;

    public JournalistImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Integer> saveImage(@RequestBody MultipartFile file)
            throws IOException {
        return ResponseEntity.ok(imageService.saveImage(file));
    }
}