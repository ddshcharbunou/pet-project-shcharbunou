package by.shcharbunou.core.exception;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException() {
        super();
    }

    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AdminNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
