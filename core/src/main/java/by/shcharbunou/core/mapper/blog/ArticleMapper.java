package by.shcharbunou.core.mapper.blog;

import by.shcharbunou.core.dto.blog.ArticleDto;
import by.shcharbunou.dal.entity.blog.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article articleDtoToArticle(ArticleDto articleDto);

    ArticleDto articleToArticleDto(Article article);
}
