package ru.ntv.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.ntv.entity.Image;
import ru.ntv.repo.ImageRepository;

import java.io.IOException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    public Image getImageById(Integer id) {
        var image = imageRepository.findById(id);
        if (image.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This image does not exist");
        }

        return image.get();
    }
    
    public Integer saveImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }
        
        return imageRepository.save(Image.builder()
                .type(file.getContentType())
                .imageData(file.getBytes())
                .build()
        ).getId();
    }
}