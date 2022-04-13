package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClaimMessage {
    CLAIM_DUPLICATE("Ошибка: Такой запрос уже существует");

    private final String message;
}
