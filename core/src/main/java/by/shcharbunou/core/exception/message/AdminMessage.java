package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminMessage {
    ADMIN_NOT_FOUND("Ошибка: Администратор не найден"),
    TEACHER_NOT_ADMIN("Ошибка: Преподаватель должен иметь права администратора");

    private final String message;
}
