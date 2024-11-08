package store.exception;

public class NotExistProductException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.";

    public NotExistProductException(String message) {
        super(ERROR_MESSAGE + "(존재하지 않는 상품 : " + message + ")");
    }
}
