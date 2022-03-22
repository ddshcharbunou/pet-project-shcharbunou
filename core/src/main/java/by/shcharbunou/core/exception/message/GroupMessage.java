package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupMessage {
    GROUP_NOT_FOUND("Ошибка: Группа не найдена");

    private final String message;
}
