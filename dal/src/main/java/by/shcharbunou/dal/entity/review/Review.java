package by.shcharbunou.dal.entity.review;

import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.entity.user.User;
import javax.persistence.*;
import lombok.*;

import javax.persistence.ManyToOne;
import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "review")
@AttributeOverride(name = "id", column = @Column(name = "review_id"))
public class Review extends BaseEntity {
    @Column(name = "review_grade", nullable = false)
    private int grade;

    @Column(name = "review_content", nullable = false)
    @Lob
    private String content;

    @Column(name = "review_date", nullable = false)
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Deprecated
    public Review() {}
}
