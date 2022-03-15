package by.shcharbunou.dal.entity.blog;

import by.shcharbunou.dal.entity.BaseEntity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
