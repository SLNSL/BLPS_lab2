package ru.ntv.controllers.common;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ntv.service.ImageService;

@RestController
@Validated
@RequestMapping("images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) {
        var image = imageService.getImageById(id);
        
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(image.getImageData());
    }
}