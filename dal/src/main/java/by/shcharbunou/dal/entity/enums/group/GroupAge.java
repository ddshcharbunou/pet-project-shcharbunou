package by.shcharbunou.dal.entity.enums.group;

import lombok.Getter;

public enum GroupAge {
    KIDS("Kids"),
    TEENS("Teens"),
    ADULTS("Adults");

    @Getter
    private final String age;

    GroupAge(String age) {
        this.age = age;
    }
}
