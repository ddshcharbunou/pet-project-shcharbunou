package by.shcharbunou.dal.entity.user.office.homework;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
public class SubTheme {
    private String designation;

    @ElementCollection
    @AttributeOverride(name = "link", column = @Column(name = "homework_link"))
    private List<HomeworkLink> links = new ArrayList<>();

    public void connectLink(HomeworkLink link) {
        this.links.add(link);
    }
}
