package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeMessage {
    INVALID_TIME_FORMAT("Ошибка: Время введено неправильно (00:00)");

    private final String message;
}
