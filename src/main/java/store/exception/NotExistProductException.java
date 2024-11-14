package store.exception;

public class NotExistProductException extends IllegalArgumentException {
    public NotExistProductException(String message) {
        super(ErrorMessage.NOT_EXIST_PRODUCT_EXCEPTION.getMessage() + "(존재하지 않는 상품 : " + message + ")");
    }
}
