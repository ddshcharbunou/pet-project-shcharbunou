package by.shcharbunou.dal.entity.user.office.homework;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Embeddable
public class SubTheme {
    private String designation;

    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name = "link", column = @Column(name = "homework_link")),
            @AttributeOverride(name = "taskType", column = @Column(name = "homework_task_type"))
    })
    private List<HomeworkLink> links = new ArrayList<>();

    public void connectLink(HomeworkLink link) {
        this.links.add(link);
    }
}
