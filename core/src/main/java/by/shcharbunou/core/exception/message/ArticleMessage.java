package by.shcharbunou.core.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleMessage {
    ARTICLE_NOT_FOUND("Ошибка: Статья не найдена");

    private final String message;
}
