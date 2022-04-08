package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMessage {
    USER_NOT_FOUND("Ошибка: Пользователь не найден"),
    USER_NOT_ACTIVATED("Ошибка: Пользователь не верифицирован");

    private final String message;
}
