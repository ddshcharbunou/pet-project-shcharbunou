package by.shcharbunou.core.exception;

public class ClaimDuplicateException extends Exception {
    public ClaimDuplicateException() {
        super();
    }

    public ClaimDuplicateException(String message) {
        super(message);
    }

    public ClaimDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClaimDuplicateException(Throwable cause) {
        super(cause);
    }

    protected ClaimDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
