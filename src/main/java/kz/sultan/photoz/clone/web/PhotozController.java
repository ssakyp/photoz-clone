package kz.sultan.photoz.clone.web;

import kz.sultan.photoz.clone.model.Photo;
import kz.sultan.photoz.clone.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }


    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get() {
        return photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo getById(@PathVariable Integer id) {
        Photo photo = photozService.get(id);

        if (photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id) {
          photozService.remove(id);
//        if(photo == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
    }

    @PostMapping("/photoz")
    public Photo save(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

}
