package by.shcharbunou.dal.repository.blog.gallery;

import by.shcharbunou.dal.entity.blog.gallery.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Gallery entity.
 */
@Repository("galleryRepository")
public interface GalleryRepository extends JpaRepository<Gallery, UUID> {
}
