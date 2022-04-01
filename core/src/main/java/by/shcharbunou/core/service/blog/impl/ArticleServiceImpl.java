package by.shcharbunou.core.service.blog.impl;

import by.shcharbunou.core.dto.blog.ArticleDto;
import by.shcharbunou.core.exception.ArticleNotFoundException;
import by.shcharbunou.core.exception.message.ArticleMessage;
import by.shcharbunou.core.mapper.blog.ArticleMapper;
import by.shcharbunou.core.service.blog.ArticleService;
import by.shcharbunou.dal.entity.blog.Article;
import by.shcharbunou.dal.repository.blog.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service("articleService")
@Transactional(transactionManager = "transactionManager")
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }


    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public Article findArticleById(UUID id) throws ArticleNotFoundException {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException(ArticleMessage.ARTICLE_NOT_FOUND.getMessage()));
    }

    @Override
    public Article findArticleByHeader(String header) throws ArticleNotFoundException {
        Article article = articleRepository.findByHeader(header);
        if (Objects.isNull(article)) {
            throw new ArticleNotFoundException(ArticleMessage.ARTICLE_NOT_FOUND.getMessage());
        }
        return article;
    }

    @Override
    public Article createArticle(ArticleDto articleDto) {
        return articleMapper.articleDtoToArticle(articleDto);
    }
}
