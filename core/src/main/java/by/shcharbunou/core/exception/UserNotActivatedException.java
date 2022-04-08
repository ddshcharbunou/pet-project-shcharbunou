package by.shcharbunou.core.exception;

public class UserNotActivatedException extends Exception {
    public UserNotActivatedException() {
        super();
    }

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotActivatedException(Throwable cause) {
        super(cause);
    }

    protected UserNotActivatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
