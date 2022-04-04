package by.shcharbunou.core.exception;

public class TimeFormatException extends Exception {
    public TimeFormatException() {
        super();
    }

    public TimeFormatException(String message) {
        super(message);
    }

    public TimeFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeFormatException(Throwable cause) {
        super(cause);
    }

    protected TimeFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
