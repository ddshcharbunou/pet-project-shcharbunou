package by.shcharbunou.core.service.blog;

import by.shcharbunou.core.dto.blog.ArticleDto;
import by.shcharbunou.core.exception.ArticleNotFoundException;
import by.shcharbunou.dal.entity.blog.Article;

import java.util.UUID;

public interface ArticleService {
    Article saveArticle(Article article);

    void deleteArticle(Article article);

    Article findArticleById(UUID id) throws ArticleNotFoundException;

    Article findArticleByHeader(String header) throws ArticleNotFoundException;

    Article createArticle(ArticleDto articleDto);
}
