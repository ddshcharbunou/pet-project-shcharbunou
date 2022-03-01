package by.shcharbunou.dal.entity.enums.group;

import lombok.Getter;

public enum GroupDesignation {
    BEGINNER("Beginner"),
    ELEMENTARY1_3("Elementary 1-3"),
    ELEMENTARY4("Elementary 4"),
    PRE_INTERMEDIATE1_3("Pre-intermediate 1-3"),
    PRE_INTERMEDIATE4("Pre-intermediate 4"),
    LOW_INTERMEDIATE("Low-intermediate"),
    MID_INTERMEDIATE("Mid-intermediate"),
    UPPER_INTERMEDIATE1_3("Upper-intermediate 1-3"),
    PRE_ADVANCED1_3("Pre-advanced 1-3"),
    LOWER_ADVANCED1_3("Lower-advanced 1-3"),
    UPPER_ADVANCED1_3("Upper-advanced 1-3");

    @Getter
    private final String designation;

    GroupDesignation(String designation) {
        this.designation = designation;
    }
}
