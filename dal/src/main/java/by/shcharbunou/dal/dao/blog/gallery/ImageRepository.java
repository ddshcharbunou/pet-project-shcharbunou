package by.shcharbunou.dal.dao.blog.gallery;

import by.shcharbunou.dal.entity.blog.gallery.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
