package store.exception;

public class InvalidProductFormatException extends IllegalArgumentException {
    public InvalidProductFormatException(String message) {
        super(ErrorMessage.INVALID_PRODUCT_FORMAT_EXCEPTION.getMessage() + "(올바르지 않은 형식 : " + message + ")");
    }
}
