package store.exception;

public class InsufficientQuantityException extends IllegalArgumentException {
    public InsufficientQuantityException(String message) {
        super(ErrorMessage.INSUFFICIENT_QUANTITY_EXCEPTION.getMessage() + " (재고 수량 초과 : " + message + ")");
    }
}
