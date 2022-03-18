package by.shcharbunou.dal.dao.review;

import by.shcharbunou.dal.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
