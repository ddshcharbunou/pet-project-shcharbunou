package by.shcharbunou.dal.entity.user.office.homework;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "homework")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "homework_id")),
        @AttributeOverride(name = "designation", column = @Column(name = "homework_sub_theme_designation")),
        @AttributeOverride(name = "links", column = @Column(name = "homework_link"))
})
public class Homework extends BaseEntity {
    @Column(name = "homework_theme", nullable = false)
    private String theme;

    @Column(name = "homework_date", nullable = false)
    private Instant date;

    @Column(name = "homework_sub_theme")
    @ElementCollection
    private List<SubTheme> subThemes;
}
