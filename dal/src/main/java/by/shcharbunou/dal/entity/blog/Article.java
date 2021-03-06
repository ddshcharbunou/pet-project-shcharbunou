package by.shcharbunou.dal.entity.blog;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AllArgsConstructor

@Entity
@Table(name = "article")
@AttributeOverride(name = "id", column = @Column(name = "article_id"))
public class Article extends BaseEntity {
    @Column(name = "article_header", nullable = false)
    private String header;

    @Column(name = "article_content", nullable = false)
    @Lob
    private String content;

    @Column(name = "article_date", nullable = false)
    private Instant date;

    @Deprecated
    public Article() {}
}
