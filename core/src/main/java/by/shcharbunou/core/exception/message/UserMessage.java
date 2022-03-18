package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMessage {
    USER_NOT_FOUND("Error: User not found");

    private final String message;
}
