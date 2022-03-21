package by.shcharbunou.dal.entity.user.office.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor

@Embeddable
public class HomeworkLink {
    private String taskType;

    private String link;

    @Deprecated
    public HomeworkLink() {}
}
