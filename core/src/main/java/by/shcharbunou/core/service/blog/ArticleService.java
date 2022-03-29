package by.shcharbunou.core.service.blog;

import by.shcharbunou.core.exception.ArticleNotFoundException;
import by.shcharbunou.dal.entity.blog.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface ArticleService {
    Article saveArticle(Article article);

    void deleteArticle(Article article);

    Article findArticleById(UUID id) throws ArticleNotFoundException;

    Article findArticleByHeader(String header) throws ArticleNotFoundException;

    Article createArticle(HttpServletRequest request, Article article);
}
