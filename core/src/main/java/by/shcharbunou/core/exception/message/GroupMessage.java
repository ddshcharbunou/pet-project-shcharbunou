package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupMessage {
    GROUP_NOT_FOUND("Ошибка: Группа не найдена"),
    GROUP_DUPLICATE("Ошибка: Такая группа уже существует");

    private final String message;
}
