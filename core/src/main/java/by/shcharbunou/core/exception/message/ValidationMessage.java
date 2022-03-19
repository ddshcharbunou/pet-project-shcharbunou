package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationMessage {
    EMAIL_VALIDATION_MESSAGE("Ошибка: Некорректный email адрес"),
    PHONE_VALIDATION_MESSAGE("Ошибка: Телефон должен быть введён в межународном формате"),
    NAME_AND_SURNAME_VALIDATION_MESSAGE("Ошибка: Имя и фамилия должны содержать не менее двух символов"),
    PASSWORD_VALIDATION_MESSAGE("Ошибка: Надёжный пароль - 8 и более символов"),
    USERNAME_VALIDATION_MESSAGE("Ошибка: Имя пользователя должно содержать не менее 4 символов"),
    REPEATED_PASSWORD_VALIDATION_MESSAGE("Ошибка: Пароли не совпадают"),
    USERNAME_EXISTS("Ошибка, пользователь с таким именем уже существует"),
    EMAIL_EXISTS("Ошибка: На данный email уже зарегестрирован пользователь");

    private final String message;
}
