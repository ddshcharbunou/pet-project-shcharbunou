package by.shcharbunou.dal.entity.enums.group.connector;

import by.shcharbunou.dal.entity.enums.group.Day;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddableDay {
    @Enumerated(EnumType.STRING)
    private Day day;

    @Override
    public String toString() {
        return day.getDay();
    }
}
