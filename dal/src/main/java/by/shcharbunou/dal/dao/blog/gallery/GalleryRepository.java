package by.shcharbunou.dal.dao.blog.gallery;

import by.shcharbunou.dal.entity.blog.gallery.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("galleryRepository")
public interface GalleryRepository extends JpaRepository<Gallery, UUID> {
}
