package by.shcharbunou.dal.dao.blog.gallery;

import by.shcharbunou.dal.entity.blog.gallery.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GalleryRepository extends JpaRepository<Gallery, UUID> {
}
