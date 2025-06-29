package kz.sultan.photoz.clone.repository;

import kz.sultan.photoz.clone.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotozRepozitory extends CrudRepository<Photo, Integer> {
}
