package by.shcharbunou.dal.entity.blog;

import by.shcharbunou.dal.entity.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "article")
@AttributeOverride(name = "id", column = @Column(name = "article_id"))
public class Article extends BaseEntity {
    @Column(name = "header", nullable = false)
    public String header;

    @Deprecated
    public Article() {}
}
