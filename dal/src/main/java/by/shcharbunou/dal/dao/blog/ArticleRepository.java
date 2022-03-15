package by.shcharbunou.dal.dao.blog;

import by.shcharbunou.dal.entity.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
