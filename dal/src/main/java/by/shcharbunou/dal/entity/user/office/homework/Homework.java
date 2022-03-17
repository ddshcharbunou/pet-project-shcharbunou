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
@AttributeOverride(name = "id", column = @Column(name = "homework_id"))
public class Homework extends BaseEntity {
    @Column(name = "homework_theme", nullable = false)
    private String theme;

    @Column(name = "homework_date", nullable = false)
    private Instant date;

    @ElementCollection
    @AttributeOverride(name = "designation", column = @Column(name = "homework_sub_theme"))
    private List<SubTheme> subThemes;

    public void connectSubTheme(SubTheme subTheme) {
        this.subThemes.add(subTheme);
    }
}
