package by.shcharbunou.core.exception;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException() {
        super();
    }

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
