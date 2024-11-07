package store.exception;

public class NotExistProductException extends IllegalArgumentException {
    public NotExistProductException(String message) {
        super(message);
    }
}
