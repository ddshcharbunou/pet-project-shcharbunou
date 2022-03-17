package by.shcharbunou.dal.entity.enums.material;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaterialType {
    BOOK("Book"),
    WORKBOOK("Workbook");

    private final String type;
}
