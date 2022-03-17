package by.shcharbunou.dal.entity.enums.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupAge {
    KIDS("Kids"),
    TEENS("Teens"),
    ADULTS("Adults");

    private final String age;
}
