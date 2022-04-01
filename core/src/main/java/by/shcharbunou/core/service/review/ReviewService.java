package by.shcharbunou.core.service.review;

import by.shcharbunou.core.dto.review.request.ReviewRequest;
import by.shcharbunou.dal.entity.review.Review;

import java.util.UUID;

/**
 * Service for review entity.
 */
public interface ReviewService {
    /**
     * Save review in db.
     * @param review review
     * @return review {@link Review}
     */
    Review saveReview(Review review);

    /**
     * Find review by id.
     * @param id id
     * @return review {@link Review}
     */
    Review findReviewById(UUID id);

    /**
     * Delete review
     * @param review review
     */
    void deleteReview(Review review);

    /**
     * Create new review.
     * @param reviewRequest review request
     * @return review {@link Review}
     */
    Review createReview(ReviewRequest reviewRequest);
}
