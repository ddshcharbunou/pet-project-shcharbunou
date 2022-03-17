package by.shcharbunou.dal.entity.user.office.homework;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class HomeworkLink {
    private String link;
}
