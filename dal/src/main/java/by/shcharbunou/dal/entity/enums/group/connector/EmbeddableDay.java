package by.shcharbunou.dal.entity.enums.group.connector;

import by.shcharbunou.dal.entity.enums.group.Day;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class EmbeddableDay {
    @Enumerated(EnumType.STRING)
    private Day day;
}
