package by.shcharbunou.dal.entity.user.office.homework;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor

@Entity
@Table(name = "subtheme")
@AttributeOverride(name = "id", column = @Column(name = "subtheme_id"))
public class SubTheme extends BaseEntity {
    @Column(name = "subtheme_designation", nullable = false)
    private String designation;

    @ElementCollection
    @CollectionTable(name = "subtheme_content", joinColumns = @JoinColumn(name = "subtheme_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "link", column = @Column(name = "homework_link")),
            @AttributeOverride(name = "taskType", column = @Column(name = "homework_task_type"))
    })
    private List<HomeworkLink> links = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "homework_id", nullable = false)
    private Homework homework;

    public void connectLink(HomeworkLink link) {
        this.links.add(link);
    }

    @Deprecated
    public SubTheme() {}
}
