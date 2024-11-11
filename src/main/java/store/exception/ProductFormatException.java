package store.exception;

public class ProductFormatException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";

    public ProductFormatException(String message) {
        super(ERROR_MESSAGE + "(올바르지 않은 형식 : " + message + ")");
    }
}
