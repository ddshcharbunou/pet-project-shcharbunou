package by.shcharbunou.core.service.blog;

import by.shcharbunou.core.dto.blog.ArticleDto;
import by.shcharbunou.core.exception.ArticleNotFoundException;
import by.shcharbunou.dal.entity.blog.Article;

import java.util.UUID;

/**
 * Service for Article entity.
 */
public interface ArticleService {
    /**
     * Save article in db.
     * @param article article
     * @return article {@link Article}
     */
    Article saveArticle(Article article);

    /**
     * Delete article
     * @param article article
     */
    void deleteArticle(Article article);

    /**
     * Find article by id.
     * @param id id
     * @return article {@link Article}
     * @throws ArticleNotFoundException article not found
     */
    Article findArticleById(UUID id) throws ArticleNotFoundException;

    /**
     * Find article by header.
     * @param header header.
     * @return article {@link Article}
     * @throws ArticleNotFoundException article not found
     */
    Article findArticleByHeader(String header) throws ArticleNotFoundException;

    /**
     * Create new article with help of article dto.
     * @param articleDto articleDto
     * @return article {@link Article}
     */
    Article createArticle(ArticleDto articleDto);
}
