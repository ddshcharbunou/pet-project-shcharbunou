package by.shcharbunou.dal.entity.user.office.homework;

import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.entity.user.Group;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AllArgsConstructor

@Entity
@Table(name = "homework")
@AttributeOverride(name = "id", column = @Column(name = "homework_id"))
public class Homework extends BaseEntity {
    @Column(name = "homework_theme", nullable = false)
    private String theme;

    @Column(name = "homework_date", nullable = false)
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTheme> subThemes = new ArrayList<>();

    public void connectSubTheme(SubTheme subTheme) {
        this.subThemes.add(subTheme);
        subTheme.setHomework(this);
    }

    @Deprecated
    public Homework() {}
}
