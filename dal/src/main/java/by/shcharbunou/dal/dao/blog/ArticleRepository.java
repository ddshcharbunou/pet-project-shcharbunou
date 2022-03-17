package by.shcharbunou.dal.dao.blog;

import by.shcharbunou.dal.entity.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
