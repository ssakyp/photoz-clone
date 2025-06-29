package kz.sultan.photoz.clone.service;

import kz.sultan.photoz.clone.model.Photo;
import kz.sultan.photoz.clone.repository.PhotozRepozitory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhotozService {

//    private Map<String, Photo> db = new HashMap<>(){{
//        put("1", new Photo("1", "hello.jpg"));
//    }};

    private final PhotozRepozitory photozRepozitory;

    public PhotozService(PhotozRepozitory photozRepozitory) {
        this.photozRepozitory = photozRepozitory;
    }

    public Iterable<Photo> get() {
//        return db.values();
        return photozRepozitory.findAll();
    }

    public Photo get(Integer id) {
//        return db.get(id);
        return photozRepozitory.findById(id).orElse(null);
    }

    public void remove(Integer id) {
//        return db.remove(id);
        photozRepozitory.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
//        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);

        photo.setContentType(contentType);
        photo.setData(data);
        photozRepozitory.save(photo);
        return photo;
    }
}
