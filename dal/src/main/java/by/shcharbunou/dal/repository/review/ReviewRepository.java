package by.shcharbunou.dal.repository.review;

import by.shcharbunou.dal.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("reviewRepository")
public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
