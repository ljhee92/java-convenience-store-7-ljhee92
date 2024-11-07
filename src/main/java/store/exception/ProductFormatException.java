package store.exception;

public class ProductFormatException extends IllegalArgumentException {
    public ProductFormatException(String message) {
        super(message);
    }
}
