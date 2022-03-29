package by.shcharbunou.dal.repository.blog;

import by.shcharbunou.dal.entity.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Article entity.
 */
@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    /**
     * Find article by it header.
     * @param header header
     * @return article {@link Article}
     */
    Article findByHeader(String header);
}
