package by.shcharbunou.core.exception;

public class GroupDuplicateException extends Exception {
    public GroupDuplicateException() {
        super();
    }

    public GroupDuplicateException(String message) {
        super(message);
    }

    public GroupDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupDuplicateException(Throwable cause) {
        super(cause);
    }

    protected GroupDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
