package by.shcharbunou.dal.repository.blog.gallery;

import by.shcharbunou.dal.entity.blog.gallery.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Image entity.
 */
@Repository("imageRepository")
public interface ImageRepository extends JpaRepository<Image, UUID> {
}
