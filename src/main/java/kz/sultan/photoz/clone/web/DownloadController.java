package kz.sultan.photoz.clone.web;

import kz.sultan.photoz.clone.model.Photo;
import kz.sultan.photoz.clone.service.PhotozService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    private final PhotozService photozService;

    public DownloadController (PhotozService photozService) {
        this.photozService = photozService;
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) {
        Photo photo = photozService.get(id);

        if (photo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();

        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
