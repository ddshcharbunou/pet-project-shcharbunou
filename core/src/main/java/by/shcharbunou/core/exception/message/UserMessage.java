package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMessage {
    USER_NOT_FOUND("Ошибка: Пользователь не найден");

    private final String message;
}
