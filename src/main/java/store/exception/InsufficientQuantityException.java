package store.exception;

public class InsufficientQuantityException extends IllegalArgumentException {
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
