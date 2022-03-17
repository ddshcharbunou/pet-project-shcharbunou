package by.shcharbunou.dal.entity.user.office.homework;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Getter
@Setter
@Embeddable
@AttributeOverride(name = "link", column = @Column(name = "homework_link"))
public class SubTheme {
    private String designation;

    @ElementCollection
    private List<HomeworkLink> links;
}
